package com.skt.m;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import emulator.Emulator;
import emulator.graphics2D.IImage;
import emulator.ui.IScreen;

import java.awt.image.BufferedImage;

public class Graphics2D {
    // Field constants
    public static final int DRAW_AND = 0;
    public static final int DRAW_COPY = 1;
    public static final int DRAW_OR = 2;
    public static final int DRAW_XOR = 3;

    private final Graphics graphics;

    // Method to get Graphics2D object
    public static Graphics2D getGraphics2D(Graphics g) {
        Emulator.getEmulator().getLogStream().println("[skt.m.Graphics2D] getGraphics2D");
        if (g == null) {
            throw new NullPointerException("Graphics object cannot be null");
        }
        return new Graphics2D(g);
    }

    private Graphics2D(Graphics g) {
        Emulator.getEmulator().getLogStream().println("[skt.m.Graphics2D] Graphics2D");
        this.graphics = g;
    }

    // Static method to capture LCD image
    public static Image captureLCD(int x, int y, int w, int h) {
        Emulator.getEmulator().getLogStream().println("[skt.m.Graphics2D] captureLCD");

        IScreen scr = Emulator.getEmulator().getScreen();
        final IImage screenImage = scr.getScreenImg();
        final IImage backBufferImage2 = scr.getBackBufferImage();
        backBufferImage2.cloneImage(screenImage, x, y, w, h);

        return convertToImage(screenImage);

    }

    // Method to draw an image
    public void drawImage(int tx, int ty, Image src, int sx, int sy, int sw, int sh, int mode) {
        Emulator.getEmulator().getLogStream().println("[skt.m.Graphics2D] drawImage");
        if (src == null) {
            throw new NullPointerException("Source image cannot be null");
        }
        if (mode < DRAW_AND || mode > DRAW_XOR) {
            throw new IllegalArgumentException("Invalid mode");
        }

        // 새로운 이미지 생성 (잘라낼 부분을 포함)
        Image subImage = Image.createImage(src, sx, sy, sw, sh, 0);

        // 모드에 따라 위치 조정
        int dx = tx;
        int dy = ty;

        if ((mode & Graphics.BOTTOM) != 0) {
            dy -= sh;
        }
        if ((mode & Graphics.RIGHT) != 0) {
            dx -= sw;
        }
        if ((mode & Graphics.HCENTER) != 0) {
            dx -= sw / 2;
        }
        if ((mode & Graphics.VCENTER) != 0) {
            dy -= sh / 2;
        }

        // 그래픽 객체에 그리기
        graphics.drawImage(subImage, dx, dy, Graphics.TOP | Graphics.LEFT);
    }

    // Method to invert a rectangle
    public void invertRect(int x, int y, int w, int h) {
        Emulator.getEmulator().getLogStream().println("[skt.m.Graphics2D] invertRect");
        if (w <= 0 || h <= 0) {
            throw new IllegalArgumentException("Width and height must be greater than 0");
        }
        // Implement rectangle inversion logic here
    }

    // Method to get a pixel color
    public int getPixel(int x, int y) {
        Emulator.getEmulator().getLogStream().println("[skt.m.Graphics2D] getPixel");
        // Implement pixel color retrieval logic here
        return 0; // Example return value
    }

    // Method to set a pixel color
    public void setPixel(int x, int y, int col) {
        Emulator.getEmulator().getLogStream().println("[skt.m.Graphics2D] setPixel");
        // Implement pixel color setting logic here
    }

    // Method to get a pixel mask
    public boolean getPixelMask(int x, int y) {
        Emulator.getEmulator().getLogStream().println("[skt.m.Graphics2D] getPixelMask");
        // Implement pixel mask retrieval logic here
        return false; // Example return value
    }

    // Method to set a pixel mask
    public void setPixelMask(int x, int y, boolean mask) {
        Emulator.getEmulator().getLogStream().println("[skt.m.Graphics2D] setPixelMask");
        // Implement pixel mask setting logic here
    }

    // Static method to create a maskable image
    public static Image createMaskableImage(int width, int height) {
        Emulator.getEmulator().getLogStream().println("[skt.m.Graphics2D] createMaskableImage");
        // Implement maskable image creation logic here
        return null; // Example return value
    }

    // Utility method to convert IImage to Image
    private static Image convertToImage(IImage iImage) {
        // Assuming IImage has a method to get the raw pixel data
        int width = iImage.getWidth();
        int height = iImage.getHeight();
        int[] rgbData = new int[width * height];
        iImage.getRGB(rgbData, 0, width, 0, 0, width, height);

        return Image.createRGBImage(rgbData, width, height, false);
    }
}
