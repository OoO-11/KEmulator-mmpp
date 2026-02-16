package com.skt.m;

import emulator.Emulator;
import emulator.media.MMFPlayer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class AudioClipImpl implements AudioClip {
    private byte[] audioData;
    private final Object startLock = new Object();
    private volatile boolean stopped = false;
    private volatile boolean paused = false;
    private Thread playbackThread;
    private float len = -1;
    private int type = -1;
    public int volume = 3;
    private int offset = 0;
    private int bufferSize = 0;

    ArrayList<Track> tracks = new ArrayList<>();

    public AudioClipImpl() {
        // MMFPlayer 초기화
        MMFPlayer.mmfplayerinit();
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] AudioClipImpl created");
    }

    @Override
    public void close() throws java.io.IOException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] close");
        synchronized (startLock) {
            if (playbackThread != null && playbackThread.isAlive()) {
                stopped = true;
                startLock.notifyAll();
                try {
                    playbackThread.join(1000); // 최대 1초 대기
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            audioData = null;
            playbackThread = null;
            type = -1;
            len = -1;
            tracks.clear();

            // MMFPlayer 정리
            try {
                MMFPlayer.stop();
            } catch (Exception e) {
                // ignore
            }
        }
        // 시스템 자원 반환 로직
    }

    @Override
    public void loop() throws UserStopException, java.io.IOException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] loop");
        synchronized (startLock) {
            if (audioData == null) {
                throw new java.io.IOException("Audio data not loaded. Call open() first.");
            }

            stopped = false;
            paused = false;

            // 재생 스레드 시작 (무한 반복)
            playbackThread = new Thread(() -> {
                try {
                    long startTs = System.currentTimeMillis();
                    MMFPlayer.play(255, volume); // loopCount=255 (무한 반복)
                    long endTs = System.currentTimeMillis();
                } catch (Exception e) {
                    Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] Loop error: " + e.getMessage());
                }
            });

            playbackThread.setDaemon(true);
            playbackThread.start();

            // loop()는 stop()이 호출될 때까지 계속 block
            try {
                while (!stopped) {
                    startLock.wait();
                }

                if (stopped) {
                    throw new UserStopException("Loop was stopped by user");
                }

            } catch (InterruptedException e) {
                throw new java.io.IOException("Loop interrupted", e);
            }
        }
        // 무한 반복 연주 로직
    }

    @Override
    public void open(byte[] data, int offset, int bufferSize) throws UnsupportedFormatException, ResourceAllocException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] open");

        // 오디오 데이터에 관련된 시스템 자원 받아오는 로직
        byte[] extractedData = new byte[bufferSize];
        System.arraycopy(data, offset, extractedData, 0, bufferSize);
        this.audioData = extractedData;
        this.offset = offset;
        this.bufferSize = bufferSize;
        this.tracks.clear();

        ByteBuffer buffer = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN);
        if(buffer.getInt() != 0x4D4D4D44){
            // MMMD
            throw new UnsupportedFormatException("MMMD");
        }

        float atrLen = 0;
        for (int i = 0; i <= data.length - 4; i++) {
            if (data[i] == 0x41 && data[i + 1] == 0x54 && data[i + 2] == 0x52) {
                // ATR
                type = 1;
                atrLen = ((data[i + 4] & 0xFF) << 24) |
                        ((data[i + 5] & 0xFF) << 16) |
                        ((data[i + 6] & 0xFF) << 8) |
                        (data[i + 7] & 0xFF);
                atrLen = atrLen / 2;
//                Emulator.getEmulator().getLogStream().println("ATR Length (int): " + len);
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
//                Emulator.getEmulator().getLogStream().println("MTR Length (int): " + len);
            }


        }
        len = getDurationMillis() + atrLen;
        Emulator.getEmulator().getLogStream().println("snd Length (int): " + len);
        try {
            MMFPlayer.initPlayer(this.audioData);
        } catch (Exception e) {
            throw new ResourceAllocException("Failed to initialize MMFPlayer: " + e.getMessage());
        }
    }

    @Override
    public void pause() throws java.io.IOException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] pause");
        synchronized (startLock) {
            if (audioData == null) {
                return;
            }
            paused = true;
            try {
                MMFPlayer.pause();
            } catch (Exception e) {
                throw new java.io.IOException("Failed to pause: " + e.getMessage());
            }
        }
    }

    @Override
    public void play() throws UserStopException, java.io.IOException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] play");
        synchronized (startLock) {
            if (playbackThread != null && playbackThread.isAlive()) {
                throw new IllegalStateException("Playback already running");
            }

            stopped = false;
            paused = false;
            playbackThread = new Thread(() -> {
                try {
                    MMFPlayer.play(1, volume);
                } catch (Exception e) {
                    // 내부 재생 오류는 무시
                }
            });

            playbackThread.setDaemon(true);
            playbackThread.start();

            try {
                long start = System.currentTimeMillis();
                while (!stopped) {
                    long elapsed = System.currentTimeMillis() - start;
                    long remaining = (long)len - elapsed;
                    if (remaining <= 0) break;
                    startLock.wait(Math.max(remaining, 100)); // Math.max(remaining, 100)
                }

                if (stopped) {
                    throw new UserStopException("Playback was stopped");
                }

            } catch (InterruptedException e) {
                throw new java.io.IOException("Playback interrupted", e);
            }
        }
    }

    @Override
    public void resume() throws java.io.IOException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] resume");
        synchronized (startLock) {
            if (audioData == null) {
                return;
            }
            if (!paused) {
                return;
            }
            paused = false;
            try {
                MMFPlayer.resume();
            } catch (Exception e) {
                throw new java.io.IOException("Failed to resume: " + e.getMessage());
            }
        }
    }

    @Override
    public void stop() throws java.io.IOException, InterruptedException {
        Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] stop");
        synchronized (startLock) {
            stopped = true;
            try {
                MMFPlayer.stop();
            } catch (Exception e) {
                Emulator.getEmulator().getLogStream().println("[skt.m.AudioClipImpl] Stop error: " + e.getMessage());
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
