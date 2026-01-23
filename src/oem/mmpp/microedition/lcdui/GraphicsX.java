package mmpp.microedition.lcdui;

import emulator.debug.Profiler;
import emulator.graphics2D.IImage;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import emulator.Emulator;
import emulator.ui.IScreen;

public class GraphicsX extends Graphics {
    public static int DEFAULT_ALPHA = 256;

    public GraphicsX(IImage paramImageDelegate) {
        super(paramImageDelegate);
        Emulator.getEmulator().getLogStream().println("[mmpp] GraphicsX");
    }

    public GraphicsX(IImage paramImageDelegatem, IImage i2){
        super(paramImageDelegatem, i2);
        Emulator.getEmulator().getLogStream().println("[mmpp] GraphicsX2");
    }

    public Image capture(int x, int y, int width, int height) {
        Emulator.getEmulator().getLogStream().println("[mmpp] capture");
        if (width <= 0 || height <= 0){
            throw new IllegalArgumentException("Width and height must be greater than 0.");
        }
        if (x < 0 || y < 0 || x + width > this.getClipWidth() || y + height > this.getClipHeight()) {
            throw new IllegalArgumentException("Capture area is outside the clipping region.");
        }
        int[] pixels = new int[width * height];
        this.image.getRGB(pixels, 0, width, x, y, width, height);
        return Image.createRGBImage(pixels, width, height, true);
    }

    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints){
        Emulator.getEmulator().getLogStream().println("[mmpp] drawPolygon");
        if (xPoints == null || yPoints == null || xPoints.length != yPoints.length || nPoints <= 0 || nPoints > xPoints.length) {
            throw new IllegalArgumentException("Invalid points array or number of points.");
        }
        for (int i = 0; i < nPoints - 1; i++) {
            drawLine(xPoints[i], yPoints[i], xPoints[i + 1], yPoints[i + 1]);
        }
        drawLine(xPoints[nPoints - 1], yPoints[nPoints - 1], xPoints[0], yPoints[0]);
    }

    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints){
        Emulator.getEmulator().getLogStream().println("[mmpp] drawPolyline");
        for (int i = 0; i < nPoints - 1; i++) {
            drawLine(xPoints[i], yPoints[i], xPoints[i + 1], yPoints[i + 1]);
        }
    }

    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints){
        Emulator.getEmulator().getLogStream().println("[mmpp] fillPolygon");
        for (int i = 0; i < nPoints - 1; i++) {
            fillTriangle(xPoints[i], yPoints[i], xPoints[i + 1], yPoints[i + 1], xPoints[0], yPoints[0]);
        }
    }

    public int getPixel(int x, int y) {
        Emulator.getEmulator().getLogStream().println("[mmpp] getPixel");
        if (x < this.getClipX() || y < this.getClipY() || x >= this.getClipX() + this.getClipWidth() || y >= this.getClipY() + this.getClipHeight()) {
            throw new IllegalArgumentException("Point is outside the clipping region.");
        }
        return this.getImage().getRGB(x, y);
    }

    public void setAlpha(int alpha) {
        Emulator.getEmulator().getLogStream().println("[mmpp] setAlpha"+alpha);
        if(alpha < 0 || alpha > 256){
            throw new IllegalArgumentException("alpha should be 0~256.");
        }
        this.alpha = alpha;
    }

    public void setPaintMode() {
        Emulator.getEmulator().getLogStream().println("[mmpp] setPaintMode");
        this.xorMode = false;
    }

    public void setPixel(int x, int y, int RGB) {
        Emulator.getEmulator().getLogStream().println("[mmpp] setPixel");
        if (x < this.getClipX() || y < this.getClipY() || x >= this.getClipX() + this.getClipWidth() || y >= this.getClipY() + this.getClipHeight()) {
            throw new IllegalArgumentException("Point is outside the clipping region.");
        }
        this.getImage().setRGB(x, y, RGB);
    }

    public void setXORMode(int RGB) {
        Emulator.getEmulator().getLogStream().println("[mmpp] setXORMode" + RGB);
        this.xorMode = true;
        this.setColor(RGB);
        this.alpha = DEFAULT_ALPHA;
    }

    public void fillRect(final int x, final int y, final int width, final int height) {
        ++Profiler.drawCallCount;

        int w = Math.max(width, 1);  // 최소 너비 보장
        int h = Math.max(height, 1); // 최소 높이 보장

        Emulator.getEmulator().getLogStream().println("x " + x + " y " + y + " w " + w + " h " + h);

        IScreen scr = Emulator.getEmulator().getScreen();
        final IImage backBufferImage2 = scr.getBackBufferImage();

        int ww = x+w > 240? 240-x : w;
        int hh = y+h > 360? 360-y : h;

        int[] pixels = new int[ww * hh];
        this.image.getRGB(pixels, 0, ww, x, y, ww, hh);

//        backBufferImage2.getRGB(pixels, 0, ww, x, y, ww, hh);
//        IImage iima = Image.createRGBImage(pixels, ww, hh, true).getImpl();


        if(this.xorMode) {
            Emulator.getEmulator().getLogStream().println("[mmpp] fillRect(xor) x:" + x + " y:" + y + " w:" + width + " h:" + height + " alpha: " + this.alpha);

            // XOR 연산 수행
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] ^= this.getColor();
            }

            // 원본 이미지에 XOR 결과를 다시 덮어쓰기
            // 이게 없으면 타이틀 나머지 나오는 대신 버전이 안나오고 메뉴에 개가 검은색으로 나오고, 인트로 글이 흰색으로 나옴
            // 있으면 타이틀 xor이 잘못 적용되고 인트로도 멀쩡하게 작동함.
            // 있는게 맞는 것 같긴 한데, 타이틀 왜 잘못나오지
            this.image.setRGB(x, y, ww, hh, pixels, 0, ww);


            // 전체 이미지 다시 그리기
            this.impl.drawImage(this.image, 0, 0);

//            iima.setRGB(0, 0, ww, hh, pixels, 0, ww);
//            this.impl.drawImage(iima, 0, 0);

        }
        else{
            if(this.alpha == 256) {
                this.impl.fillRect(x, y, width, height);
            }
            else {
                Emulator.getEmulator().getLogStream().println("[mmpp] fillRect (Alpha) x:" + x + " y:" + y + " w:" + width + " h:" + height + " alpha:" + this.alpha);

                for (int i = 0; i < pixels.length; i++) {
                    // 기존 픽셀과 새 색상을 alpha 블렌딩
                    int srcR = (pixels[i] >> 16) & 0xFF;
                    int srcG = (pixels[i] >> 8) & 0xFF;
                    int srcB = pixels[i] & 0xFF;

                    int rr = srcR * this.alpha / 256;
                    int gg = srcG * this.alpha / 256;
                    int bb = srcB * this.alpha / 256;

                    pixels[i] = (rr << 16) | (gg << 8) | bb;  // 블렌딩 결과
                }

                this.image.setRGB(x, y, ww, hh, pixels, 0, ww);
                this.impl.drawImage(this.image, 0, 0);

//                iima.setRGB(0, 0, ww, hh, pixels, 0, ww);
//                this.impl.drawImage(iima, 0, 0);
            }
        }
    }

    public void drawImage(Image image, int n, int n2, int n3) {
        int w = image.getWidth();
        int h = image.getHeight();
        int[] pixels = new int[w * h];

        image.getRGB(pixels, 0, w, 0, 0, w, h);
        IImage ima = image.getImpl();

        if(this.xorMode){
            Emulator.getEmulator().getLogStream().println("[mmpp] drawImage (xor) x:" + n + " y:" + n2 + "alpha: " + this.alpha);
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] ^= this.getColor();
            }

            ima.setRGB(0, 0, w, h, pixels, 0, w);
            this.impl.drawImage(ima, n, n2);
        }
        else{
            if(this.alpha <= 256) {
                super.drawImage(image, n, n2, n3);
            }
            else {
                Emulator.getEmulator().getLogStream().println("[mmpp] drawImage (Alpha) x:" + n + " y:" + n2 + "alpha: " + this.alpha);

                int n5;
                int height;
                label32:
                {
                    if ((n3 & 8) != 0) {
                        n5 = n;
                        height = image.getWidth();
                    } else {
                        if ((n3 & 1) == 0) {
                            break label32;
                        }

                        n5 = n;
                        height = image.getWidth() / 2;
                    }

                    n = n5 - height;
                }

                label26:
                {
                    if ((n3 & 32) != 0) {
                        n5 = n2;
                        height = image.getHeight();
                    } else {
                        if ((n3 & 2) == 0) {
                            break label26;
                        }

                        n5 = n2;
                        height = image.getHeight() / 2;
                    }

                    n2 = n5 - height;
                }

                int[] destPixels = new int[w * h];

                for (int i = 0; i < pixels.length; i++) {
                    // 소스 픽셀의 ARGB 추출
                    int srcR = (pixels[i] >> 16) & 0xFF;
                    int srcG = (pixels[i] >> 8) & 0xFF;
                    int srcB = pixels[i] & 0xFF;

                    int rr = srcR * this.alpha / 256;
                    int gg = srcG * this.alpha / 256;
                    int bb = srcB * this.alpha / 256;

                    destPixels[i] = (rr << 16) | (gg << 8) | bb;
                }
                ima.setRGB(0, 0, w, h, destPixels, 0, w);
                this.impl.drawImage(ima, n, n2);
            }
        }
    }

//    public void setClip(final int n, final int n2, final int n3, final int n4) {
//        ++Profiler.drawCallCount;
//        this.impl.setClip(n, n2, n3, n4);
//        if (xrayGraphics != null)
//            this.xrayGraphics.setClip(n, n2, n3, n4);
//    }

//    public void setGrayScale(final int n) {
//        Emulator.getEmulator().getLogStream().println("[mmpp] setGrayScale:" +n);
//        if ((n & 0xFFFFFF00) > 0) {
//            throw new IllegalArgumentException();
//        }
//        this.setColor(n, n, n);
//    }
}
