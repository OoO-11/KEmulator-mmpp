package com.skt.m;

public class Device {
    // Field constants
    public static final int COLOR_MODE_256C = 0;
    public static final int COLOR_MODE_4G = 1;
    public static final int COLOR_MODE_64KC = 2;
    public static final int MELODY_MUSICBELL = 3;
    public static final int MELODY_MYBELL = 4;
    public static final int SIS_CALL = 5;
    public static final int SIS_NORMAL = 6;
    public static final int SIS_PWR_OFF = 7;
    public static final int SIS_PWR_ON = 8;
    public static final int SIS_WAP = 9;

    // Methods
    public static void beep(int freq, int duration) {
        // 간단한 비프음을 낸다.
    }

    public static void enableRestoreLCD(boolean flag) {
        // VM을 SUSPEND 모드로 전환한다.
    }

    public static void invokeWapBrowser(String url) {
        // 해당 URL로 단말기 내장 WAP 브라우저를 호출한다.
    }

    public static boolean isBacklightEnabled() {
        // 키가 눌려질 때의 백라이트 설정을 리턴한다.
        return true;
    }

    public static boolean isKeyToneEnabled() {
        // 키가 눌려질 때의 키톤 설정을 리턴한다.
        return true;
    }

    public static void setBacklightEnabled(boolean flag) {
        // 키가 눌려질 때 백라이트가 자동으로 켜지는 기능을 설정한다.
    }

    public static void setColorMode(int mode) {
        // 이미지를 로딩할 때에 설정된 칼라 모드로 메모리에 로딩하게 한다.
    }

    public static void setKeyRepeatTime(int delay, int interval) {
        // 키를 오래 누르고 있으면 Canvas 클래스의 keyRepeated() 메소드가 호출된다.
    }

    public static void setKeyToneEnabled(boolean flag) {
        // 키가 눌려질때 자동으로 키톤이 발생하는 기능을 설정한다.
    }

    public static boolean setMelody(int type, String title, byte[] data) {
        // 멜로디를 현재의 벨소리를 저장한다.
        return true;
    }

    public static boolean setSISImage(int type, String title, byte[] data) {
        // SIS 이미지를 현재의 그림 친구로 저장한다.
        return true;
    }

    public static void setNAI(int flags) {
        // 사용하지 말것
    }
}
