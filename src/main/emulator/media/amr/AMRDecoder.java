package emulator.media.amr;

import emulator.i;

public class AMRDecoder {
	static boolean aBoolean444;

	public AMRDecoder() {
		super();
	}

	public static native short[] decode(final short[] p0, final int p1);

	public static boolean a() {
		if (AMRDecoder.aBoolean444) {
			return true;
		}
		try {
			i.a("amrdecoder");
			AMRDecoder.aBoolean444 = true;
			return AMRDecoder.aBoolean444;
		} catch (UnsatisfiedLinkError ignored) {
		} catch (Exception ignored) {
		}
		AMRDecoder.aBoolean444 = false;
		return AMRDecoder.aBoolean444;
	}
}
