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
import com.samsung.util.AudioClip;

import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MediaPlayer {
	private Player player;
	private AudioClip audio;
	private boolean loop;
	private String currentVolume = String.valueOf(MAX_VOLUME);
	private static final int MAX_VOLUME = 5;

	public void setMediaLocation(String location) {
		Emulator.getEmulator().getLogStream().println("[mmpp] setMediaLocation to " + location);
		try {
//			InputStream is = CustomJarResources.getResourceAsStream(null, location);
//			player = Manager.createPlayer(is, "audio/mmf");
//			player.realize();
//			is.close();
			audio = new AudioClip(AudioClip.TYPE_MMF, location);
		} catch (IOException e) {
			e.printStackTrace();
//		} catch (MediaException e) {
//			e.printStackTrace();
		}
	}

	public void setMediaSource(byte[] buffer, int offset, int length) {
//		try {
//			ByteArrayInputStream bis = new ByteArrayInputStream(buffer, offset, length);
//			player = Manager.createPlayer(bis, "audio/mmf");
//			player.realize();
//			bis.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (MediaException e) {
//			e.printStackTrace();
//		}
		Emulator.getEmulator().getLogStream().println("[mmpp] setMediaSource");
		audio = new AudioClip(AudioClip.TYPE_MMF, buffer, offset, length);
	}

	public void setVolumeLevel(String level) {
		Emulator.getEmulator().getLogStream().println("[mmpp] setVolumeLevel to " + level);
		if(audio == null)
			return;
        audio.volume = Integer.parseInt(level);
		currentVolume = level;
	}

	public String getVolumeLevel() {
		Emulator.getEmulator().getLogStream().println("[mmpp] getVolumeLevel " + currentVolume);
		return currentVolume;
	}

	public void start() {
//		try {
//			player.start();
//
//		} catch (MediaException e) {
//			e.printStackTrace();
//		}
		Emulator.getEmulator().getLogStream().println("[mmpp] start " + currentVolume);
		if(audio == null)
			return;
		audio.play(1, Integer.parseInt(currentVolume));
	}

	public void pause() {
//		try {
//			player.stop();
//		} catch (MediaException e) {
//			e.printStackTrace();
//		}
		Emulator.getEmulator().getLogStream().println("[mmpp] pause");
		audio.pause();
	}

	public void resume() {
//		try {
//			player.start();
//		} catch (MediaException e) {
//			e.printStackTrace();
//		}
		Emulator.getEmulator().getLogStream().println("[mmpp] resume" + currentVolume);
		if(audio == null)
			return;
		audio.resume();
	}

	public void stop() {
//		if(player != null){
//			player.close();
//		}
		Emulator.getEmulator().getLogStream().println("[mmpp] stop");
		if(audio != null) {
			audio.stop();
		}
	}

	public void setPlayBackLoop(boolean val) {
		Emulator.getEmulator().getLogStream().println("[mmpp] setPlayBackLoop " + val);
		loop = val;
//		int loopCount = val ? -1 : 1;
//		player.setLoopCount(loopCount);
	}

	public boolean getPlayBackLoop() {
		Emulator.getEmulator().getLogStream().println("[mmpp] getPlayBackLoop");
		return loop;
	}
}