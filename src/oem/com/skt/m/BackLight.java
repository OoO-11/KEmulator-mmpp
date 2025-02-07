package com.skt.m;

import emulator.Emulator;

public class BackLight {
    // Field constants
    public static final int BACKLIGHT_ON = 1;
    public static final int BACKLIGHT_OFF = 0;

    // Methods
    public static int getColor() {
        // 현재 설정되어 있는 BackLight Color의 인덱스를 받아온다.
//        Emulator.getEmulator().getLogStream().println("[skt.m.BackLight] getColor");
        return 0; // 예시 반환값
    }

    public static int[] getColors() {
        // BackLight Color 들을 얻어온다. 배열 안의 각 integer는 RGB (0x00rrggbb)값이다.
//        Emulator.getEmulator().getLogStream().println("[skt.m.BackLight] getColors");
        return new int[]{0x00FF00, 0x0000FF}; // 예시 반환값
    }

    public static int getColorNum() {
        // 백라이트에 사용할 수 있는 총 Color갯수를 가져온다.
//        Emulator.getEmulator().getLogStream().println("[skt.m.BackLight] getColorNum");
        return 2; // 예시 반환값
    }

    public static void setColor(int index) {
        // BackLight의 칼라 Index를 설정한다.
//        Emulator.getEmulator().getLogStream().println("[skt.m.BackLight] setColor");
    }

    public static void on(int timeout) {
        // BackLight를 timeout 동안 킨다.
//        Emulator.getEmulator().getLogStream().println("[skt.m.BackLight] on");
    }

    public static void off() {
        // BackLight를 끈다.
//        Emulator.getEmulator().getLogStream().println("[skt.m.BackLight] off");
    }
}
