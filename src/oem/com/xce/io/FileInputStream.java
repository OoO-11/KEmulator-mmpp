package com.xce.io;

import emulator.Emulator;

import java.io.IOException;
import java.io.InputStream;

public class FileInputStream extends InputStream{
    public static final int STDIN = 0;
    private int fd;
    private String name;
    private XFile file;

//    public FileInputStream(int fd) {
//        if (fd != STDIN) {
//            throw new IllegalArgumentException("fd must be STDIN");
//        }
//        this.fd = fd;
//    }

    public FileInputStream(String name) throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.FileInputStream]  init");
        this.name = name;
        this.file = new XFile(name, XFile.READ);
        // Implementation to open the specified file to read
    }

    public FileInputStream(XFile file) throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.FileInputStream]  init");
        this.file = file;
        // Implementation to open the specified file to read
    }

    public int available() throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.FileInputStream]  available");
        throw new RuntimeException("Not implemented yet.");
        // Implementation to check how many bytes are left to read
//        return 0;
    }

    public void close() throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.FileInputStream]  close");
        file.close();
    }

    public void mark(int readlimit) {
        Emulator.getEmulator().getLogStream().println("[xce.io.FileInputStream]  mark");
        throw new RuntimeException("Not implemented yet.");
        // Implementation to mark the current position for future use
    }

    public boolean markSupported() {
        Emulator.getEmulator().getLogStream().println("[xce.io.FileInputStream]  markSupported");
        throw new RuntimeException("Not implemented yet.");
//        return true; // FileInputStream supports mark
    }

    public int read() throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.FileInputStream]  read");
        byte[] b = new byte[1];
        return this.file.read(b,0,1);
    }

    public int read(byte[] b) throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.FileInputStream]  read2");
        return read(b, 0, b.length);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.FileInputStream]  read3");
        return this.file.read(b,off,len);
    }

    public void reset() throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.FileInputStream]  reset");
        throw new RuntimeException("Not implemented yet.");
        // Implementation to move to the marked position
    }

    public long skip(long n) throws IOException {
        if (n > Integer.MAX_VALUE) {
            throw new IOException("cannot skip that far");
        }
        return file.seek((int) n, XFile.SEEK_CUR);
    }
}
