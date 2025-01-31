package com.skt.m;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import emulator.Emulator;
import emulator.graphics2D.IImage;
import emulator.ui.IScreen;

public class Graphics2D {
    // Field constants
    public static final int DRAW_AND = 0;
    public static final int DRAW_COPY = 1;
    public static final int DRAW_OR = 2;
    public static final int DRAW_XOR = 3;

     // 2가 OR일때 초성이 안보임
    //

    private static Graphics graphics;
    private static Image image;
    private static boolean[][] pixelMask;

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
        IScreen scr = Emulator.getEmulator().getScreen();
        final IImage screenImage = scr.getScreenImg();
        graphics = g;
        image = convertToImage(screenImage);
    }

    // Static method to capture LCD image
    public static Image captureLCD(int x, int y, int w, int h) {
        Emulator.getEmulator().getLogStream().println("[skt.m.Graphics2D] captureLCD");
        return Image.createImage(image, x, y, w, h, 0);
    }

    // Method to draw an image
    public void drawImage(int tx, int ty, Image src, int sx, int sy, int sw, int sh, int mode) {
        Emulator.getEmulator().getLogStream().println("[skt.m.Graphics2D] drawImage "+mode);
        if (src == null) {
            throw new NullPointerException("Source image cannot be null");
        }
//        if (mode < DRAW_AND || mode > DRAW_XOR) {
//            throw new IllegalArgumentException("Invalid mode");
//        }

        // 기존 이미지에서 잘라내기
        int[] srcPixels = new int[sw * sh];
        src.getRGB(srcPixels, 0, sw, sx, sy, sw, sh);

        // 기존 화면 이미지에서 해당 영역 가져오기
        int[] destPixels = new int[sw * sh];
        image.getRGB(destPixels, 0, sw, tx, ty, sw, sh);

        for (int i = 0; i < srcPixels.length; i++) {
            switch (mode) {
                case DRAW_AND:
                    srcPixels[i] &= destPixels[i];
                    break;
                case DRAW_COPY:
                    srcPixels[i] = destPixels[i];
                    break;
                case DRAW_OR:
                    srcPixels[i] |= destPixels[i];
                    break;
                case DRAW_XOR:
                    srcPixels[i] ^= destPixels[i];
                    break;
            }
        }

        // 결과를 새로운 이미지에 적용
        Image processedImage = Image.createImage(sw, sh);
        Graphics g = processedImage.getGraphics();
        g.drawRGB(srcPixels, 0, sw, 0, 0, sw, sh, false);

        graphics.drawImage(processedImage, tx, ty, Graphics.TOP | Graphics.LEFT);
    }

    // Method to invert a rectangle
    public void invertRect(int x, int y, int w, int h) {
        Emulator.getEmulator().getLogStream().println("[skt.m.Graphics2D] invertRect");
        if (w <= 0 || h <= 0) {
            throw new IllegalArgumentException("Width and height must be greater than 0");
        }

        // 대상 영역의 픽셀을 가져오기 위해 원본 화면을 복사한 Image 생성
        Image img = Image.createImage(w, h);
        Graphics tempG = img.getGraphics();
        tempG.drawImage(image, -x, -y, Graphics.TOP | Graphics.LEFT); // 원본 화면을 복사

        int[] rgbData = new int[w * h];
        img.getRGB(rgbData, 0, w, 0, 0, w, h); // RGB 데이터 추출

        // 픽셀 색상을 반전 (네거티브 효과)
        for (int i = 0; i < rgbData.length; i++) {
            int color = rgbData[i];

            // 색상 반전 (알파값 유지)
            int alpha = color & 0xFF000000;
            int invertedColor = alpha | (~color & 0x00FFFFFF);

            rgbData[i] = invertedColor;
        }

        Image invertedImg = Image.createRGBImage(rgbData, w, h, true);
        graphics.drawImage(invertedImg, x, y, Graphics.TOP | Graphics.LEFT);
    }

    // Method to get a pixel color
    public int getPixel(int x, int y) {
        Emulator.getEmulator().getLogStream().println("[skt.m.Graphics2D] getPixel");
        int[] rgbData = new int[1];
        image.getRGB(rgbData, 0, 1, x, y, 1, 1);
        return rgbData[0];
    }

    // Method to set a pixel color
    public void setPixel(int x, int y, int col) {
        Emulator.getEmulator().getLogStream().println("[skt.m.Graphics2D] setPixel");
        int[] rgbData = new int[1];
        rgbData[0] = col;
        graphics.drawRGB(rgbData, 0, 1, x, y, 1, 1, true);
    }

    // Method to get a pixel mask
    public boolean getPixelMask(int x, int y) {
        Emulator.getEmulator().getLogStream().println("[skt.m.Graphics2D] getPixelMask");
        if (x < 0 || x >= pixelMask.length || y < 0 || y >= pixelMask[0].length) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        return pixelMask[x][y];
    }

    // Method to set a pixel mask
    public void setPixelMask(int x, int y, boolean mask) {
        Emulator.getEmulator().getLogStream().println("[skt.m.Graphics2D] setPixelMask");
        if (x < 0 || x >= pixelMask.length || y < 0 || y >= pixelMask[0].length) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        pixelMask[x][y] = mask;
        // Implement pixel mask setting logic here
    }

    // Static method to create a maskable image
    public static Image createMaskableImage(int width, int height) {
        Emulator.getEmulator().getLogStream().println("[skt.m.Graphics2D] createMaskableImage");

        // 새로운 이미지 생성
        Image maskableImage = Image.createImage(width, height);
        Graphics g = maskableImage.getGraphics();
        pixelMask = new boolean[width][height];

        // 초기화 작업 (필요에 따라 추가 작업 가능)
//        g.setColor(0x000000); // 검은색으로 초기화
//        g.fillRect(0, 0, width, height);
        // white by default?

        return maskableImage;
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
