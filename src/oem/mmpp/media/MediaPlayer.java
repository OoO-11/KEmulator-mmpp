/*
 * Copyright 2020 Nikita Shakarun
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mmpp.media;

import emulator.Emulator;
import emulator.custom.CustomJarResources;
import emulator.media.MMFPlayer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MediaPlayer {
	private boolean loop;
	private String currentVolume = String.valueOf(MAX_VOLUME);
	private static final int MAX_VOLUME = 5;
	private final Object startLock = new Object();

	private byte[] data;
	private int loopCount;
	private int volume;

	private Thread workerThread;
	private BlockingQueue<Runnable> commandQueue;
	private volatile boolean running = true;

	public MediaPlayer(){
		MMFPlayer.mmfplayerinit();
		commandQueue = new LinkedBlockingQueue<>();
		workerThread = new Thread(this::processCommands);
		workerThread.setDaemon(true);
		workerThread.start();
	}

	private void processCommands() {
		while (running) {
			try {
				Runnable command = commandQueue.take();
				command.run();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		}
	}

	public void setMediaLocation(String location) throws IOException{
		Emulator.getEmulator().getLogStream().println("[mmpp] setMediaLocation to " + location);
		try {
			commandQueue.put(() -> {
				if (MMFPlayer.isPlaying()) {
					MMFPlayer.stop();
				}
				try {
					InputStream is = CustomJarResources.getResourceAsStream(location);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] b = new byte[512];
					while (is.available() > 0) {
						int n2 = is.read(b);
						baos.write(b, 0, n2);
					}
					this.data = baos.toByteArray();
					MMFPlayer.initPlayer(this.data);
					Emulator.getEmulator().getLogStream().println("[mmpp] setMediaLocation act");
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			});
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public void setMediaSource(byte[] buffer, int offset, int length) {
		Emulator.getEmulator().getLogStream().println("[mmpp] setMediaSource");
		try {
			commandQueue.put(() -> {
				if (MMFPlayer.isPlaying()) {
					MMFPlayer.stop();
				}
				this.data = new byte[length];
				System.arraycopy(buffer, offset, this.data, 0, length);
				MMFPlayer.initPlayer(this.data);
			});
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public void setMediaSource(byte[] buffer) {
		Emulator.getEmulator().getLogStream().println("[mmpp] setMediaSource");
		try {
			commandQueue.put(() -> {
				if (MMFPlayer.isPlaying()) {
					MMFPlayer.stop();
				}
				this.data = buffer.clone();
				MMFPlayer.initPlayer(this.data);
			});
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public void setVolumeLevel(String level) {
		Emulator.getEmulator().getLogStream().println("[mmpp] setVolumeLevel to " + level);
		this.volume = Integer.parseInt(level);
		currentVolume = level;
	}

	public String getVolumeLevel() {
		Emulator.getEmulator().getLogStream().println("[mmpp] getVolumeLevel " + currentVolume);
		return currentVolume;
	}

	public void start() {
		Emulator.getEmulator().getLogStream().println("[mmpp] start " + currentVolume);
		synchronized (startLock) {
			// Emulator.getEmulator().getLogStream().println("[mmpp] start " + currentVolume);
			try {
				commandQueue.put(() -> {
					if (MMFPlayer.isPlaying()) {
						MMFPlayer.stop();
					}
					this.loopCount = loop ? 255 : 1;
					MMFPlayer.playSafe(this.loopCount, this.volume);
					Emulator.getEmulator().getLogStream().println("[mmpp] start act" + currentVolume);
				});
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

	public void pause() {
		Emulator.getEmulator().getLogStream().println("[mmpp] pause");
		try {
			commandQueue.put(() -> MMFPlayer.pause());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public void resume() {
		Emulator.getEmulator().getLogStream().println("[mmpp] resume");
		if(data == null)
			return;
		try {
			commandQueue.put(() -> MMFPlayer.resume());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public void stop() {
		Emulator.getEmulator().getLogStream().println("[mmpp] stop");
		if(data != null) {
			try {
				commandQueue.clear();
				commandQueue.put(() -> MMFPlayer.stop());
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

	public void setPlayBackLoop(boolean val) {
		Emulator.getEmulator().getLogStream().println("[mmpp] setPlayBackLoop " + val);
		loop = val;
	}

	public boolean isPlayBackLoop() {
		Emulator.getEmulator().getLogStream().println("[mmpp] getPlayBackLoop");
		return loop;
	}
}