package com.skt.m;

import emulator.Emulator;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class AudioClipImpl implements AudioClip {

    com.samsung.util.AudioClip AC;
    private final Object startLock = new Object();
    private volatile boolean stopped = false;
    private Thread playbackThread;
    private float len = -1;
    private int type = -1;

    ArrayList<Track> tracks = new ArrayList<>();

    @Override
    public void close() throws java.io.IOException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] close");
        synchronized (startLock) {
            AC = null;
            playbackThread = null;
//        stopped = false;
            type = -1;
            len = -1;
            tracks.clear();
        }
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
        ByteBuffer buffer = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN);
        // 오디오 데이터에 관련된 시스템 자원 받아오는 로직

        byte[] temp = new byte[4];
        if(buffer.getInt() != 0x4D4D4D44){
            // MMMD
            throw new UnsupportedFormatException("MMMD");
        }

        for (int i = 0; i <= data.length - 4; i++) {
            if (data[i] == 0x41 && data[i + 1] == 0x54 && data[i + 2] == 0x52) {
                // ATR
                type = 1;
                len = ((data[i + 4] & 0xFF) << 24) |
                        ((data[i + 5] & 0xFF) << 16) |
                        ((data[i + 6] & 0xFF) << 8) |
                        (data[i + 7] & 0xFF);
                len = len / 2;
                Emulator.getEmulator().getLogStream().println("ATR Length (int): " + len);
            }
            else if (data[i] == 0x4D && data[i + 1] == 0x54 && data[i + 2] == 0x52) {
                // MTR
                int length = ((data[i + 4] & 0xFF) << 24) |
                            ((data[i + 5] & 0xFF) << 16) |
                            ((data[i + 6] & 0xFF) << 8) |
                            (data[i + 7] & 0xFF);

                ByteBuffer subBuffer = ByteBuffer.wrap(data, i+8, length);
                tracks.add(track(data[i + 3] & 0xFF, subBuffer));
                i += length + 7;
                len = getDurationMillis();
                Emulator.getEmulator().getLogStream().println("MTR Length (int): " + len);
            }
        }
//        System.out.println(getDurationMillis());
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
            stopped = false;
            playbackThread = new Thread(() -> {
                try {
                    AC.play(1, 3);  // 실제 재생 호출
                } catch (Exception e) {
                    // 내부 재생 오류는 무시
                }
            });

            playbackThread.start();

            try {
                long start = System.currentTimeMillis();
                while (!stopped) {
                    long elapsed = System.currentTimeMillis() - start;
                    long remaining = (long)len - elapsed;
                    if (remaining <= 0) break;
                    startLock.wait(remaining);
                }

                if (stopped) {
                    throw new UserStopException("Playback was stopped");
                }

            } catch (InterruptedException e) {
                throw new java.io.IOException("Playback interrupted", e);
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
    public void stop() throws java.io.IOException, InterruptedException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] stop");
        synchronized (startLock) {
            if (AC == null) {
                return;
            }
            AC.stop();
            stopped = true;
            if (playbackThread != null && playbackThread.isAlive()) {
                playbackThread.interrupt();
                playbackThread.join(); // 안전하게 종료 기다림
                playbackThread = null;
            }

            startLock.notifyAll();
        }
        // 연주 멈춤 로직
    }

    class Track {
        int sequenceType;

        // timebases are in milliseconds
        int timebaseD;
        int timebaseG;

        int channelStatus;

        private int tmpOctaveShift = 0;

        int index = 0;
        ArrayList<SequenceEvent> sequenceEvents = new ArrayList<>();

    }

    static class SequenceEvent {
        int duration;
    }

    private SequenceEvent sequenceEvent(ByteBuffer reader, Track track) {
        SequenceEvent event = new SequenceEvent();
        event.duration = decodeVarInt(reader);
        byte firstByte = reader.get();

        if ((firstByte & 0xFF) == 0xFF) {
            int begin = reader.get();
            if (begin == 0) {
                return event;
            }
            else if ((begin & 0xFF) == 0xF0) {
                int eventSize = reader.get(); // includes maker + format + data + 0xF7
                byte[] a = new byte[eventSize];
                reader.get(a);
            }
        }
        else if (firstByte == 0) {
            int secondByte = reader.get();
            if (secondByte == 0) {
                if (reader.get() == 0) {
                    return event;
                }
            }

            byte eventClass = (byte) ((secondByte >> 4) & 0x3);
            byte eventId = (byte) (secondByte & 0xF);

            if (eventClass == 3) {
                reader.get();
            }
        }
        else {
            // NoteEvent: read gate time (variable-length)
            decodeVarInt(reader); // discard
        }
        return event;
    }

    private int decodeVarInt(ByteBuffer reader) {
        int firstByte = reader.get();
        if ((firstByte & 0x80) == 0) {
            return firstByte;
        } else {
            int secondByte = reader.get();
            return (((firstByte & 0x7F) << 7) | secondByte) + 128;
        }
    }

    private Track track(int index, ByteBuffer che) {
        // Working variables
        Track ret = new Track();
        ret.index = index;

        che.get();
        ret.tmpOctaveShift = 0;
        ret.sequenceType = (che.get() & 0xFF);
        ret.timebaseD = convertTimebase(che.get() & 0xFF);
        ret.timebaseG = convertTimebase(che.get() & 0xFF);

        ret.channelStatus = (che.getShort() & 0xFFFF);

        while (che.remaining() > 0) {

            int id = che.getInt();
            int chunklen = che.getInt();

            ByteBuffer chunk = che.slice();
            chunk.limit(chunklen);
            che.position(che.position() + chunklen);

            if (id == 0x4D747375) {
                // MTsu
            }
            else if (id == 0x4D747371) {
                // Mtsq
                while (chunk.remaining() > 0){
                    ret.sequenceEvents.add(sequenceEvent(chunk, ret));
                }
            }
        }
        return ret;
    }

    private static final int[] TIMEBASE_TABLE = {1, 2, 4, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 20, 40, 50};

    private int convertTimebase(int timebase) {
        return TIMEBASE_TABLE[timebase];
    }

    public int getDurationMillis() {
        int maxMillis = 0;
        for (Track track : tracks) {
            int totalTicks = 0;
            for (SequenceEvent event : track.sequenceEvents) {
                totalTicks += event.duration;
            }
            // timebaseD: 드럼, timebaseG: 일반
            int timebase = (track.sequenceType == 1) ? track.timebaseD : track.timebaseG;
            int millis = totalTicks * timebase;
            if (millis > maxMillis) {
                maxMillis = millis;
            }
        }
        return maxMillis;
    }
}
