package com.xce.lcdui;

import com.skt.m.Graphics2D;
import emulator.Emulator;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class XDisplay {
    public static int width = Emulator.getEmulator().getScreen().getBackBufferImage().getWidth();
    public static int height = Emulator.getEmulator().getScreen().getBackBufferImage().getHeight();
    public static int height2 = Emulator.getEmulator().getScreen().getBackBufferImage().getHeight();

    public static void refresh(int x, int y, int width, int height) {
//        Emulator.getEmulator().getLogStream().println("[xce.lcdui.XDisplay] refresh");
        Emulator.getEventQueue().gameGraphicsFlush(x,y,width,height);
//        Emulator.getEventQueue().queueRepaint(x,y,width,height);
    }

    public static void drawImageEx(
            Graphics gfx, Image image,
            int tx, int ty,
            Image srcImage,
            int sx, int sy,
            int sw, int sh,
            int mode) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.XDisplay] drawImageEx");
        Graphics2D.getGraphics2D(gfx).drawImage(tx, ty, srcImage, sx, sy, sw, sh, mode);
    }

    // TODO : is this right?
    public static void clear(Graphics g, Image i, int x, int y){
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.XDisplay] clear");
        if(g == null){
            System.out.println("g null");
        }
        if(i == null){
            g.setColor(0xFFFFFF); // 흰색으로 설정
            g.fillRect(x, y, g.getImage().getWidth(), g.getImage().getHeight());
        }
        else{
            g.setColor(0xFFFFFF); // 흰색으로 설정
            g.fillRect(x, y, i.getWidth(), i.getHeight());
        }
    }

    // TODO : is this right?
    public static void copyLCD(Graphics g, Image i, int x, int y, int w, int h){
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.XDisplay] copyLCD"+x+" "+y+" "+" "+w+" "+h);
        Image img = Graphics2D.captureLCD(x, y, w, h);
        Graphics imgGraphics = i.getGraphics();
        imgGraphics.drawImage(img, 0, 0, Graphics.TOP | Graphics.LEFT);
    }
}
