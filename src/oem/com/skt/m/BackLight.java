package com.skt.m;

public class BackLight {
    // Field constants
    public static final int BACKLIGHT_ON = 1;
    public static final int BACKLIGHT_OFF = 0;

    // Methods
    public static int getColor() {
        // 현재 설정되어 있는 BackLight Color의 인덱스를 받아온다.
        return 0; // 예시 반환값
    }

    public static int[] getColors() {
        // BackLight Color 들을 얻어온다. 배열 안의 각 integer는 RGB (0x00rrggbb)값이다.
        return new int[]{0x00FF00, 0x0000FF}; // 예시 반환값
    }

    public static int getColorNum() {
        // 백라이트에 사용할 수 있는 총 Color갯수를 가져온다.
        return 2; // 예시 반환값
    }

    public static void setColor(int index) {
        // BackLight의 칼라 Index를 설정한다.
    }

    public static void on(int timeout) {
        // BackLight를 timeout 동안 킨다.
    }

    public static void off() {
        // BackLight를 끈다.
    }
}
