package com.xce.io;

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
        this.name = name;
        this.file = new XFile(name, XFile.READ);
        // Implementation to open the specified file to read
    }

    public FileInputStream(XFile file) throws IOException {
        this.file = file;
        // Implementation to open the specified file to read
    }

    public int available() throws IOException {
        // Implementation to check how many bytes are left to read
        return 0;
    }

    public void close() throws IOException {
        // Implementation to close the file
    }

    public void mark(int readlimit) {
        // Implementation to mark the current position for future use
    }

    public boolean markSupported() {
        return true; // FileInputStream supports mark
    }

    public int read() throws IOException {
        // Implementation to read a byte
        return 0;
    }

    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        // Implementation to read from file to b with length len
        return 0;
    }

    public void reset() throws IOException {
        // Implementation to move to the marked position
    }

    public long skip(long n) throws IOException {
        // Implementation to skip n bytes from file
        return 0;
    }
}
