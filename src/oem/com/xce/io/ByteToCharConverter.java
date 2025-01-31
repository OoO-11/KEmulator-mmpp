/*
original code from https://github.com/usernameak/sktemu
 */
package com.xce.io;

import emulator.Emulator;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ByteToCharConverter {
    private final CharsetDecoder decoder;
    private static final Charset charset = Charset.forName("EUC_KR");

    private static final byte[] emptyByteArray = new byte[0];

    protected ByteToCharConverter(CharsetDecoder decoder) {
        this.decoder = decoder;
    }

    // 기본 변환기 반환 메소드
    public static ByteToCharConverter getDefault() {
        return new ByteToCharConverter(charset.newDecoder());
    }

    public int convert(byte[] input, int inStart, int inLength, char[] output, int outStart, int outLength) {
        ByteBuffer bb = ByteBuffer.wrap(input, inStart, inLength);
        CharBuffer cb = CharBuffer.wrap(output, outStart, outLength);
        decoder.decode(bb, cb, false);
        return cb.position() - outStart;
    }

    public int flush(char[] output, int outStart, int outLength) {
        ByteBuffer bb = ByteBuffer.wrap(emptyByteArray);
        CharBuffer cb = CharBuffer.wrap(output, outStart, outLength);
        decoder.decode(bb, cb, true);
        decoder.flush(cb);
        decoder.reset();
        return cb.position() - outStart;
    }
}
