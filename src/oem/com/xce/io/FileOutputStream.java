package com.xce.io;

import emulator.Emulator;

import java.io.IOException;
import java.io.OutputStream;

public class FileOutputStream extends OutputStream{
    public static final int STDOUT = 1;
    public static final int STDERR = 2;
    private XFile file;

    public FileOutputStream(int fd) {
        Emulator.getEmulator().getLogStream().println("[xce.io.FileOutputStream]  init1");
        if (fd != STDOUT && fd != STDERR) {
            throw new IllegalArgumentException("fd must be STDOUT or STDERR");
        }
        // Implementation to open STDOUT or STDERR
    }

    public FileOutputStream(String name) throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.FileOutputStream]  init2");
        // Implementation to open the specified file to write
        this.file = new XFile(name, XFile.WRITE);
        // Additional implementation details
    }

    public FileOutputStream(String name, boolean truncate) throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.FileOutputStream]  init3");
        // Implementation to open the specified file to write (or append)
        this.file = new XFile(name, XFile.WRITE);
        if (truncate && XFile.exists(name)) {
//            file.truncate();
        }
        // Additional implementation details
    }

    public FileOutputStream(XFile file) throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.FileOutputStream]  init");
        this.file = file;
        // Implementation to open the specified file to write
    }

    public void close() throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.FileOutputStream]  close");
        // Implementation to close the file
    }

    public void flush() throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.FileOutputStream]  flush");
        // Implementation to flush the file
    }

    public void write(byte[] b, int off, int len) throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.FileOutputStream]  write");
        // Implementation to write b to the file
    }

    public void write(int b) throws IOException {
        Emulator.getEmulator().getLogStream().println("[xce.io.FileOutputStream]  write");
        // Implementation to write a byte
    }
}