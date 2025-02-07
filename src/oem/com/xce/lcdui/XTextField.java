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
    private int cursorPos = 0;

    private int x, y, width, height;

    private TextField textField;
    private Canvas canvas;
    private Graphics g;

    public XTextField(String text, int maxSize, int constraints, Canvas canvas) {
//        super("", text, maxSize, constraints);
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  init");
        if(text == null)
            this.text = "";
        this.text = text;
        this.maxSize = maxSize;
        this.constraints = constraints;
        this.canvas = canvas;

        this.textField = new TextField("",text,maxSize,constraints);
    }

    public void setText(String s) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  setText");
        if (text == null) {
            text = "";
        }
        this.text = text;

        if (cursorPos > text.length()) {
            cursorPos = text.length();
        }

        textField.setString(s);
    }

    public String getText() {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  getText");
        return text;
//        return textField.getString();
    }

    public void keyPressed(int keyCode) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  keyPressed "+keyCode);
        // 키 입력 처리
        inputChar((char)keyCode);
        System.out.println(text);
        repaint();
    }

    public void keyReleased(int keyCode) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  keyReleased" + keyCode);
//        canvas._invokeKeyReleased(keyCode);
//        Emulator.getCanvas()._invokeKeyReleased(keyCode);
    }

    public void keyRepeated(int keyCode) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  keyRepeated");
        // 키 반복 처리
//        canvas._invokeKeyRepeated(keyCode);
//        Emulator.getCanvas()._invokeKeyRepeated(keyCode);
    }

    public void paint(Graphics g) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  paint" + text);
        // Painting 처리
        g.setColor(0xFFFFFFFF);
        g.fillRect(x, y, width, height);
        g.setColor(0xFF000000);
        g.drawRect(x, y, width, height);

        g.drawString(text, x + 1, y + 1, Graphics.LEFT | Graphics.TOP);

//        g.drawString(textField.getString(), textField.getCaretPosition(), 0, Graphics.TOP | Graphics.LEFT);
    }

    public void repaint() {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  repaint");
        // Repainting 처리
        Emulator.getEmulator().getScreen().getBackBufferImage().getGraphics().drawString(text, x+1, y+1);
    }

    public void setMaxSize(int maxSize) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  setMaxSize");
        this.maxSize = maxSize;

        textField.setMaxSize(maxSize);
    }

    public int getMaxSize() {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  getMaxSize");
        return this.maxSize;
//        return textField.getMaxSize();
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
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  setFocus" + focus);
        this.focus = focus;
    }

    public void inputChar(char key) {
        Emulator.getEmulator().getLogStream().println("[xce.io.XTextField]  inputChar " + key);
        textField.insert(new String(new char[]{key}), textField.getCaretPosition());
        if(!focus){
            System.out.println("return");
            return;
        }
        if (key == 8) {
            if (cursorPos == 0) return;

            text = text.substring(0, cursorPos - 1) + text.substring(cursorPos);
            cursorPos--;
            return;
        }
        if(text.length() > maxSize){
            return;
        }
        text = text.substring(0, cursorPos) + key + text.substring(cursorPos);
        cursorPos++;
    }
}
