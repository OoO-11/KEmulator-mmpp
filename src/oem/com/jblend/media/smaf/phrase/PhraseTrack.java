/*
 * Copyright 2023 Yury Kharchenko
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

package com.jblend.media.smaf.phrase;

public class PhraseTrack extends PhraseTrackBase {
	private Phrase phrase;

	PhraseTrack(int id) {
		super(id);
	}

	public void setPhrase(Phrase p) {
		phrase = p;
	}

	public Phrase getPhrase() {
		return phrase;
	}

	public void removePhrase() {
	}

	public void setSubjectTo(PhraseTrack master) {
	}

	public PhraseTrack getSyncMaster() {
		return null;
	}
}