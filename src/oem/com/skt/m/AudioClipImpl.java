package com.skt.m;

import emulator.Emulator;

public class AudioClipImpl implements AudioClip {

    com.samsung.util.AudioClip AC;
    private final Object startLock = new Object();
    private float len = -1;
    private int type = -1;

    @Override
    public void close() throws java.io.IOException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] close");
        AC = null;
        type = -1;
        len = -1;
        // 시스템 자원 반환 로직
    }

    @Override
    public void loop() throws UserStopException, java.io.IOException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] loop");
        synchronized (startLock) {
            if (AC == null) {
                return;
            }
            AC.play(255, 3);
        }
        // 무한 반복 연주 로직
    }

    @Override
    public void open(byte[] data, int offset, int bufferSize) throws UnsupportedFormatException, ResourceAllocException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] open");
        AC = new com.samsung.util.AudioClip(1,data,offset,bufferSize);
        // 오디오 데이터에 관련된 시스템 자원 받아오는 로직

        byte[] target = {0x41, 0x54, 0x52}; // ATR 시퀀스

        for (int i = 0; i <= data.length - 4; i++) {
            if (data[i] == 0x41 && data[i + 1] == 0x54 && data[i + 2] == 0x52) {
                type = 1;
                len = ((data[i + 4] & 0xFF) << 24) |
                        ((data[i + 5] & 0xFF) << 16) |
                        ((data[i + 6] & 0xFF) << 8) |
                        (data[i + 7] & 0xFF);
                len = len * 2 / 4000;
//                System.out.println("Length (int): " + len);
            } else if (data[i] == 0x4D && data[i + 1] == 0x74 && data[i + 2] == 0x73 && data[i + 3] == 0x71) {
                type = 2;
            }
        }
    }

    @Override
    public void pause() throws java.io.IOException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] pause");
        if(AC==null){
            return;
        }
        AC.pause();
        // 연주 일시 중지 로직
    }

    @Override
    public void play() throws UserStopException, java.io.IOException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] play");
        synchronized (startLock) {
            if (AC == null) {
                return;
            }
            AC.play(1, 3);
            try {
                if(len > 0) {
                    Thread.sleep((long) (len * 1000));
                }
            }
            catch(Exception ignored){
            }
        }
        // 연주 시작 로직
    }

    @Override
    public void resume() throws java.io.IOException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] resume");
        if(AC==null){
            return;
        }
        AC.resume();
//        // 연주 계속 로직
    }

    @Override
    public void stop() throws java.io.IOException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] stop");
        synchronized (startLock) {
            if (AC == null) {
                return;
            }
            AC.stop();
        }
        // 연주 멈춤 로직
    }
}
