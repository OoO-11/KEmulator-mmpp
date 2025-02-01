package com.skt.m;

import emulator.Emulator;
import emulator.media.MMFPlayer;
//import com.samsung.util.AudioClip;

public class AudioClipImpl implements AudioClip {

    com.samsung.util.AudioClip AC;

    @Override
    public void close() throws java.io.IOException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] close");
        AC = null;
        // 시스템 자원 반환 로직
    }

    @Override
    public void loop() throws UserStopException, java.io.IOException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] loop");
        if(AC==null){
            return;
        }
        AC.play(255,3);
        // 무한 반복 연주 로직
    }

    @Override
    public void open(byte[] data, int offset, int bufferSize) throws UnsupportedFormatException, ResourceAllocException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] open");
        AC = new com.samsung.util.AudioClip(1,data,offset,bufferSize);
        // 오디오 데이터에 관련된 시스템 자원 받아오는 로직
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
        if(AC==null){
            return;
        }
        AC.play(1,3);
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
        if(AC==null){
            return;
        }
        AC.stop();
        // 연주 멈춤 로직
    }
}
