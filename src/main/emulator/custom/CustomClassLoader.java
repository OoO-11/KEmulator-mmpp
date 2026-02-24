package emulator.custom;

import emulator.Emulator;
import emulator.Settings;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public final class CustomClassLoader extends ClassLoader {
	private boolean renamedScanDone = false;

	public CustomClassLoader(final ClassLoader classLoader) {
		super(classLoader);
	}

	public final synchronized Class loadClass(final String s, final boolean b) throws ClassNotFoundException {
		boolean midlet = Emulator.jarClasses.contains(s);
		if (!midlet && isProtected(s, true)) {
			Emulator.getEmulator().getLogStream().println("Protected class: " + s);
			throw new ClassNotFoundException("Protected class: " + s);
		}
		final Class<?> loadedClass;
		if ((loadedClass = this.findLoadedClass(s)) != null) {
			return loadedClass;
		}
		if (!midlet) {
			try {
				return super.loadClass(s, b);
			} catch (ClassNotFoundException ex) {
				final Class method806;
				if ((method806 = this.loadLibraryClass(s)) == null) {
					throw ex;
				}
				return method806;
			}
		}
		final Class class1 = this.findClass(s);
		if (b) {
			this.resolveClass(class1);
		}
		return class1;
	}

	private Class loadLibraryClass(final String s) {
		Class<?> defineClass = null;
		try {
			for (int i = 0; i < Emulator.jarLibrarys.size(); ++i) {
				final ZipFile zipFile;
				final ZipEntry entry;
				if ((entry = (zipFile = new ZipFile((String) Emulator.jarLibrarys.get(i))).getEntry(s.replace('.', '/') + ".class")) != null) {
					final ClassReader classReader = new ClassReader(zipFile.getInputStream(entry));
					final ClassWriter classWriter = new ClassWriter(0);
					classReader.accept(new ClassVisitor(Opcodes.ASM4, classWriter) {}, Settings.asmSkipDebug ? ClassReader.SKIP_DEBUG : 0);
					final byte[] byteArray = classWriter.toByteArray();
					defineClass = this.defineClass(s, byteArray, 0, byteArray.length);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return defineClass;
	}

	protected final Class findClass(final String s) throws ClassNotFoundException {
		Class<?> defineClass;
		try {
			byte[] bytes;
			try {
				bytes = load(s);
			} catch (ArrayIndexOutOfBoundsException e) {
				if (Settings.asmSkipDebug) throw e;
				Settings.asmSkipDebug = true;
				bytes = load(s);
			}
			defineClass = this.defineClass(s, bytes, 0, bytes.length);
		} catch (ClassNotFoundException e) {
			return super.findClass(s);
		} catch (Exception e) {
			e.printStackTrace();
			return super.findClass(s);
		}
		return defineClass;
	}

	private byte[] load(String s) throws Exception {
		ensureRenamedClassesScanned();

		InputStream inputStream;
		if (Emulator.midletJar == null) {
			final File fileFromClassPath;
			if ((fileFromClassPath = Emulator.getFileFromClassPath(s.replace('.', '/') + ".class")) == null || !fileFromClassPath.exists()) {
				throw new ClassNotFoundException();
			}
			inputStream = new FileInputStream(fileFromClassPath);
		} else {
			final ZipFile zipFile;
			final ZipEntry entry;
			if ((entry = (zipFile = new ZipFile(Emulator.midletJar)).getEntry(s.replace('.', '/') + ".class")) == null) {
				throw new ClassNotFoundException();
			}
			inputStream = zipFile.getInputStream(entry);
		}

		final ClassReader classReader = new ClassReader(inputStream);
		final ClassWriter classWriter = new ClassWriter(0);
		try {
			classReader.accept(new CustomClassAdapter(classWriter, s), Settings.asmSkipDebug ? ClassReader.SKIP_DEBUG : 0);
		} finally {
			inputStream.close();
		}
		return classWriter.toByteArray();
	}

	private void ensureRenamedClassesScanned() {
		if (CustomClassAdapter.hasRenamedMethods || renamedScanDone) return;
		renamedScanDone = true;
		try {
			for (Object o : Emulator.jarClasses) {
				String clsDot = (String) o;
				String path = clsDot.replace('.', '/') + ".class";
				InputStream is = null;
				if (Emulator.midletJar != null) {
					ZipFile zf = new ZipFile(Emulator.midletJar);
					ZipEntry e = zf.getEntry(path);
					if (e != null) is = zf.getInputStream(e);
				} else {
					File f = Emulator.getFileFromClassPath(path);
					if (f != null && f.exists()) is = new FileInputStream(f);
				}
				if (is == null) continue;
				try {
					ClassReader cr = new ClassReader(is);
					final boolean[] will = new boolean[1];
					cr.accept(new ClassVisitor(Opcodes.ASM9) {
						String superName;
						@Override
						public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
							this.superName = superName;
							super.visit(version, access, name, signature, superName, interfaces);
						}
						@Override
						public org.objectweb.asm.MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
							if ("()V".equals(descriptor) && ("stop".equals(name) || "resume".equals(name) || "suspend".equals(name))) {
								if ("java/lang/Thread".equals(this.superName)) will[0] = true;
							}
							return super.visitMethod(access, name, descriptor, signature, exceptions);
						}
					}, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
					if (will[0]) {
						CustomClassAdapter.hasRenamedMethods = true;
						CustomClassAdapter.renamedClasses.add(clsDot.replace('.', '/'));
					}
				} finally {
					try { is.close(); } catch (Exception ignored) {}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static boolean isProtected(String s, boolean stack) {
		if (Settings.protectedPackages == null || Settings.protectedPackages.isEmpty())
			return false;

		if (s.startsWith("__")) return true;

		if (stack) {
			StackTraceElement[] st = Thread.currentThread().getStackTrace();
			if ((st.length > 4 && !Emulator.jarClasses.contains(st[4].getClassName()))) {
				if (!st[4].getMethodName().equals("forName0") ||
						(st.length > 6 && !Emulator.jarClasses.contains(st[6].getClassName())))
					return false;
			}
		}

		if (Settings.protectedPackages.contains(s))
			return true;
		int idx = -1;
		while ((idx = s.indexOf('.', idx + 1)) != -1) {
			if (Settings.protectedPackages.contains(s.substring(0, idx))) return true;
		}
		return false;
	}

	public final InputStream getResourceAsStream(final String s) {
		return super.getResourceAsStream(s);
	}
}
