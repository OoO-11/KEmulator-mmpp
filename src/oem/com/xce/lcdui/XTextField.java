package com.xce.lcdui;

import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Canvas;

import javax.microedition.lcdui.Graphics;

import emulator.Emulator;

public class XTextField {
    private String text;
    private int maxSize;
    private boolean focus;
    private int constraints;

    public XTextField(String text, int maxSize, int constraints, Canvas canvas) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  init");
        if(text == null)
            this.text = "";
        this.text = text;
        this.maxSize = maxSize;
        this.constraints = constraints;
    }

    public void setText(String s) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  setText");
        this.text = s;
    }

    public String getText() {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  getText");
        return this.text;
    }

    public void keyPressed(int keyCode) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  keyPressed");
        // 키 입력 처리
    }

    public void keyReleased(int keyCode) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  keyReleased");
        // 키 릴리즈 처리
    }

    public void keyRepeated(int keyCode) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  keyRepeated");
        // 키 반복 처리
    }

    public void paint(Graphics g) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  paint");
        // Painting 처리
    }

    public void repaint() {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  repaint");
        // Repainting 처리
    }

    public void setMaxSize(int maxSize) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  setMaxSize");
        this.maxSize = maxSize;
    }

    public int getMaxSize() {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  getMaxSize");
        return this.maxSize;
    }

    public void setBounds(int x, int y, int width, int height) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  setBounds");
        // Bounds 설정
    }

    public boolean hasFocus() {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  hasFocus");
        return this.focus;
    }

    public void setFocus(boolean focus) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  setFocus");
        this.focus = focus;
    }

    public void inputChar(char key) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  inputChar");
        // 문자 입력 처리
    }
}
