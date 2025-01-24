package com.skt.m;


import emulator.Emulator;

public final class Vibration {
    public static boolean isSupported() {
        Emulator.getEmulator().getLogStream().println("[skt.m.Vibration] isSupported");
        return false;
    }

    public static int getLevelNum() {
        Emulator.getEmulator().getLogStream().println("[skt.m.Vibration] getLevelNum");
        return 1;
    }

    public static void start(int level, int timeout) {
        Emulator.getEmulator().getLogStream().println("[skt.m.Vibration] start");
    }

    public static void stop() {
        Emulator.getEmulator().getLogStream().println("[skt.m.Vibration] stop");
    }
}
