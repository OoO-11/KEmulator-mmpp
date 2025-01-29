package emulator.media;

import emulator.Emulator;
import emulator.i;

public class MMFPlayer {
	static boolean initialized;

	public MMFPlayer() {
		super();
	}

	public static boolean mmfplayerinit() {
		if (MMFPlayer.initialized) {
			return true;
		}
//		if (Emulator.isX64()) {
//			return false;
//		}
		try {
			i.a("mmfplayer");
			int result = initMMFLibrary(Emulator.getAbsolutePath() + "/ma3smwemu.dll");
			System.out.println("mmf init "+result);
			return MMFPlayer.initialized = true;
		} catch (Throwable e) {
			System.err.println("Exception during MMFPlayer initialization:");
			e.printStackTrace(); // 에러 내용을 출력해서 확인
		}
		return MMFPlayer.initialized = false;
	}

	public static void close() {
		if (MMFPlayer.initialized) {
			stop();
			destroy();
			MMFPlayer.initialized = false;
		}
	}

	public static void playSafe(final int p0, final int p1) {
		if (!initialized) {
			System.err.println("MMFPlayer is not initialized! play() aborted.");
			return;
		}
		play(p0, p1);
	}


	public static native int initMMFLibrary(final String p0);

	public static native void initPlayer(final byte[] p0);

	public static native void play(final int p0, final int p1);

	public static native void destroy();

	public static native boolean isPlaying();

	public static native void stop();

	public static native void pause();

	public static native void resume();
}
