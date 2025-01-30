package com.xce.io;

import emulator.Emulator;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.*;

public class XFile {
    public static final int STDSTREAM = 0;
    public static final int NORMAL = 1;
    public static final int DIRECTORY = 2;
    public static final int FILE_JAR = 3;

    public static final int STDIN = 0;
    public static final int STDOUT = 1;
    public static final int STDERR = 2;

    public static final int SEEK_SET = 0;
    public static final int SEEK_CUR = 1;
    public static final int SEEK_END = 2;

    public static final int READ = 1;
    public static final int WRITE = 2;
    public static final int READ_WRITE = 3;
    public static final int READ_DIRECTORY = 4;
    public static final int READ_RESOURCE = 8;

    protected int type;
    protected int mode;
    protected int fd;
    protected int offset;
    protected byte[] buf;
    private final File file;
    private final ZipFile zipFile;
    private final RandomAccessFile raf;

//    public XFile(int fd) {
//        this.fd = fd;
//        // Additional implementation details
//    }

    public XFile(String name, int mode) throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.XFile]  init : " + name);
        this.zipFile = null;
        this.mode = mode;
        this.file = new File(Emulator.zipPath, name);
        if (mode == READ) {
            this.raf = new RandomAccessFile(this.file, "r");
        } else if (mode == WRITE) {
            this.raf = new RandomAccessFile(this.file, "rw");
        } else if (mode == READ_WRITE) {
            this.raf = new RandomAccessFile(this.file, "rw");
        } else {
            throw new IllegalArgumentException("invalid XFile mode " + mode);
        }
        // Implementation to open the specified file with the given mode
    }

    public XFile(String jarfile, String name) throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.XFile]  init : " + jarfile+" "+name);
        this.file = new File(Emulator.zipPath, jarfile);

        final ZipEntry entry;
        if ((entry = (this.zipFile = new ZipFile(file)).getEntry(name)) == null) {
            throw new IOException();
        }

        final byte[] array = new byte[(int) entry.getSize()];
        new DataInputStream(zipFile.getInputStream(entry)).readFully(array);

        File tempFile = File.createTempFile("temp", null);
        try (java.io.FileOutputStream fos = new java.io.FileOutputStream(String.valueOf(tempFile))) {
            fos.write(array);
        }

        // Initialize RandomAccessFile with the temporary file
        this.raf = new RandomAccessFile(tempFile, "rw");
        // Implementation to open the specified file within a jar
    }

    public int available() throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.XFile]  available");
        // Implementation to check how many bytes are left to read
        return (int) (raf.length() - raf.getFilePointer());
    }

    public void close() throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.XFile]  close");
        raf.close();
        // Implementation to close the file
    }

    public int read(byte[] b, int off, int len) throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.XFile]  read");
        return raf.read(b, off, len);
    }

    public static void mkdir(String dirname) throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.XFile]  mkdir "+ dirname);
        File directory = new File(Emulator.zipPath, dirname);
        if (!directory.exists()) {
            boolean result = directory.mkdir(); // 디렉토리 생성
        } else {
            System.out.println("디렉토리가 이미 존재합니다.");
        }
        // Implementation to create a directory
    }

    public static void rmrdir(String dirname) throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.XFile]  rmrdir");
        throw new RuntimeException("Not implemented yet.");
        // Implementation to remove a directory and its contents
    }

    public static void rmdir(String dirname) throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.XFile]  rmdir");
        throw new RuntimeException("Not implemented yet.");
        // Implementation to remove a directory
    }

    public String readdir() throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.XFile]  readdir");
        throw new RuntimeException("Not implemented yet.");
        // Implementation to read a directory
//        return null;
    }

    public int write(byte[] b, int off, int len) throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.XFile]  write");
        raf.write(b, off, len);
        return len;
    }

    public void flush() throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.XFile]  flush");
        throw new RuntimeException("Not implemented yet.");
        // Implementation to flush the file
    }

    public int seek(int n, int whence) throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.XFile]  seek");
        throw new RuntimeException("Not implemented yet.");
        // Implementation to seek to a specific position in the file
//        return 0;
    }

    public static boolean exists(String name) throws IOException {
        File f = new File(Emulator.zipPath, name);
        Emulator.getEmulator().getLogStream().println("[xce.io.XFile]  exists : " + name + " " + f.exists());
        return f.exists();
    }

    public static int filesize(String name) throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.XFile]  filesize");
        throw new RuntimeException("Not implemented yet.");
        // Implementation to get the size of a file
//        return 0;
    }

    public static int unlink(String name) throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.XFile]  unlink");
        throw new RuntimeException("Not implemented yet.");
        // Implementation to delete a file
//        return 0;
    }

    public static int fsused() {
        Emulator.getEmulator().getLogStream().println("[xce.io.XFile]  fsused");
        throw new RuntimeException("Not implemented yet.");
        // Implementation to get the used file system space
//        return 0;
    }

    public static int fsavail() {
        Emulator.getEmulator().getLogStream().println("[xce.io.XFile]  fsavail");
        // Implementation to get the available file system space
        return 1000000000;
    }
}
