/*
original code from https://github.com/usernameak/sktemu
 */
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

    private int x, y, width, height;

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
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  keyPressed "+keyCode);
        // 키 입력 처리
        inputChar('a');
//        throw new RuntimeException("Not implemented yet.");
    }

    public void keyReleased(int keyCode) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  keyReleased");
        // 키 릴리즈 처리
//        throw new RuntimeException("Not implemented yet.");
    }

    public void keyRepeated(int keyCode) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  keyRepeated");
        // 키 반복 처리
        throw new RuntimeException("Not implemented yet.");
    }

    public void paint(Graphics g) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  paint");
        // Painting 처리
        g.setColor(0xFFFFFFFF);
        g.fillRect(x, y, width, height);
        g.setColor(0xFF000000);
        g.drawRect(x, y, width, height);

        g.drawString(text, x + 1, y + 1, Graphics.LEFT | Graphics.TOP);
    }

    public void repaint() {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  repaint");
        // Repainting 처리
//        throw new RuntimeException("Not implemented yet.");
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
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  inputChar " + key);
        if(!focus){
            return;
        }
        if(key == 8){
            if(!text.isEmpty()){
                text = text.substring(0, text.length()-1);
            }
            else if(text.length() < maxSize){
                text += key;
            }
        }
//        repaint();
    }
}
