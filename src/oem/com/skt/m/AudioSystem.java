package com.skt.m;

import emulator.Emulator;
import com.skt.m.AudioClipImpl;

import java.io.IOException;

public final class AudioSystem {
    // Method to get supported audio formats
    public static String[] getClipFormats() {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioSystem] getClipFormats");
        // 단말기에서 지원하는 모든 오디오 포맷을 얻어온다.
        return new String[]{"mmf", "ma1", "ma2", "buzzer", "evrc"}; // 예시 반환값
    }

    // Method to get an AudioClip object for a given format
    public static AudioClip getAudioClip(String format) throws UnsupportedFormatException, IOException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioSystem] getAudioClip");
        // 해당 포맷에 맞는 새로운 AudioClip 객체를 생성한다.
        if (!isSupportedFormat(format)) {
            throw new UnsupportedFormatException("지원하지 않는 형식입니다: " + format);
        }
        return new AudioClipImpl(); // 예시 반환값
    }

    // Method to get the maximum volume for a given format
    public static int getMaxVolume(String format) throws UnsupportedFormatException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioSystem] getMaxVolume");
        // 주어진 오디오 클립 포맷의 최대 볼륨값을 얻어온다.
        if (!isSupportedFormat(format)) {
            throw new UnsupportedFormatException("지원하지 않는 형식입니다: " + format);
        }
        return 5; // 예시 반환값
    }

    // Method to get the current volume for a given format
    public static int getVolume(String format) throws UnsupportedFormatException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioSystem] getVolume");
        // 주어진 오디오 클립 형식의 볼륨값을 얻어온다.
        if (!isSupportedFormat(format)) {
            throw new UnsupportedFormatException("지원하지 않는 형식입니다: " + format);
        }
        return 3; // 예시 반환값
    }

    // Method to set the volume for a given format
    public static void setVolume(String format, int level) throws UnsupportedFormatException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioSystem] setVolume");
        // 주어진 오디오 클립 형식의 볼륨을 설정한다.
        if (!isSupportedFormat(format)) {
            throw new UnsupportedFormatException("지원하지 않는 형식입니다: " + format);
        }
        if (level < 0 || level > getMaxVolume(format)) {
            throw new IllegalArgumentException("볼륨 레벨은 0과 최대 볼륨 사이여야 합니다.");
        }
        // 볼륨 설정 로직 구현
    }

    // Helper method to check if the format is supported
    private static boolean isSupportedFormat(String format) {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioSystem] isSupportedFormat "+format);
        String[] supportedFormats = getClipFormats();
        for (String supportedFormat : supportedFormats) {
            if (supportedFormat.equals(format)) {
                return true;
            }
        }
        return false;
    }
}
