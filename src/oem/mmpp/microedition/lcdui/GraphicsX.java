package mmpp.microedition.lcdui;

import emulator.debug.Profiler;
import emulator.graphics2D.IImage;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import emulator.Emulator;
import emulator.ui.IScreen;

public class GraphicsX extends Graphics {
    public static int DEFAULT_ALPHA = 256;
    public int xorcolor = 0;

    public GraphicsX(IImage paramImageDelegate) {
        super(paramImageDelegate);
        Emulator.getEmulator().getLogStream().println("[mmpp] GraphicsX");
    }

    public GraphicsX(IImage paramImageDelegatem, IImage i2){
        super(paramImageDelegatem, i2);
        Emulator.getEmulator().getLogStream().println("[mmpp] GraphicsX2");
    }

    public Image capture(int x, int y, int width, int height) {
        Emulator.getEmulator().getLogStream().println("[mmpp] capture"+ x +" "+y+" "+width+" "+height);
        if (width <= 0 || height <= 0){
            throw new IllegalArgumentException("Width and height must be greater than 0.");
        }
        if (x < this.getClipX() || y < this.getClipY() || width > this.getClipWidth() || height > this.getClipHeight()) {
            throw new IllegalArgumentException("Capture area is outside the clipping region.");
        }

        int ww, hh, xx, yy;
        int imgW = this.image.getWidth();
        int imgH = this.image.getHeight();

        Emulator.getEmulator().getLogStream().println("[mmpp] capture"+imgW+" "+imgH);

        xx = Math.max(0, x);
        int maxX = Math.min(imgW, x + width);
        ww = maxX - xx;

        yy = Math.max(0, y);
        int maxY = Math.min(imgH, y + height);
        hh = maxY - yy;


        int[] pixels = new int[ww * hh];
        this.image.getRGB(pixels, 0, ww, xx, yy, ww, hh);
        return Image.createRGBImage(pixels, ww, hh, true);
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

    public void setXORMode(int RGB) {
        Emulator.getEmulator().getLogStream().println("[mmpp] setXORMode" + RGB);
        this.xorMode = true;
        this.xorcolor = RGB;
        this.alpha = DEFAULT_ALPHA;
    }

    public void setPixel(int x, int y, int RGB) {
        Emulator.getEmulator().getLogStream().println("[mmpp] setPixel");
        if (x < this.getClipX() || y < this.getClipY() || x >= this.getClipX() + this.getClipWidth() || y >= this.getClipY() + this.getClipHeight()) {
            throw new IllegalArgumentException("Point is outside the clipping region.");
        }
        this.getImage().setRGB(x, y, RGB);
    }


    public void fillRect(final int x, final int y, final int width, final int height) {
        ++Profiler.drawCallCount;

        int ww, hh, xx, yy;
        int imgW = this.image.getWidth();
        int imgH = this.image.getHeight();

        xx = Math.max(0, x);
        int maxX = Math.min(imgW, x + width);
        ww = maxX - xx;

        yy = Math.max(0, y);
        int maxY = Math.min(imgH, y + height);
        hh = maxY - yy;

        if (ww <= 0 || hh <= 0) {
            return;
        }

        int[] pixels = new int[ww * hh];
        this.image.getRGB(pixels, 0, ww, xx, yy, ww, hh);

        if(this.xorMode) {
            Emulator.getEmulator().getLogStream().println("[mmpp] fillRect(xor) x:" + x + " y:" + y + " w:" + width + " h:" + height + " alpha: " + this.alpha);

            int currentColor = this.getColor();
            // XOR 연산 수행
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] ^= (currentColor ^ this.xorcolor);
            }

            this.image.setRGB(xx, yy, ww, hh, pixels, 0, ww);
//            drawRGB(pixels, 0, ww, xx, yy, ww, hh, true);

        }
        else{
            if(this.alpha >= 255) {
                this.impl.fillRect(x, y, width, height);
            }
            else {
                Emulator.getEmulator().getLogStream().println("[mmpp] fillRect (Alpha) x:" + x + " y:" + y + " w:" + width + " h:" + height + " alpha:" + this.alpha);
                int srcRGB = this.getColor(); // 현재 설정된 브러시 색상
                int srcR = (srcRGB >> 16) & 0xFF;
                int srcG = (srcRGB >> 8) & 0xFF;
                int srcB = srcRGB & 0xFF;
                for (int i = 0; i < pixels.length; i++) {
                    int destR = (pixels[i] >> 16) & 0xFF;
                    int destG = (pixels[i] >> 8) & 0xFF;
                    int destB = pixels[i] & 0xFF;

                    int rr = (srcR * this.alpha + destR * (256 - this.alpha)) >> 8;
                    int gg = (srcG * this.alpha + destG * (256 - this.alpha)) >> 8;
                    int bb = (srcB * this.alpha + destB * (256 - this.alpha)) >> 8;

                    pixels[i] = (rr << 16) | (gg << 8) | bb;
                }

                this.image.setRGB(xx, yy, ww, hh, pixels, 0, ww);
//                this.impl.drawImage(this.image, 0, 0);

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
            int currentColor = this.getColor();
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] ^= (currentColor ^ this.xorcolor);
            }

            ima.setRGB(0, 0, w, h, pixels, 0, w);
            this.impl.drawImage(ima, n, n2);
        }
        else{
            if(this.alpha >= 255) {
                this.impl.setAlpha(255);
                super.drawImage(image, n, n2, n3);
            }
            else {
                Emulator.getEmulator().getLogStream().println("[mmpp] drawImage (Alpha) x:" + n + " y:" + n2 + "n3: " + n3 + "alpha: " + this.alpha);

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

                this.impl.setAlpha(this.alpha);
                this.impl.drawImage(ima, n, n2);
            }
        }
    }
}
