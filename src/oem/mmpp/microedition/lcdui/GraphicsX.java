package mmpp.microedition.lcdui;

import com.sun.midp.lcdui.ImageDelegate;
import emulator.graphics2D.IImage;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import emulator.Emulator;

public class GraphicsX extends Graphics {
    public static int DEFAULT_ALPHA = 256;

    private boolean xorMode = false;
    private int xorColor = 0;
    private int alpha = 256;

    public GraphicsX(IImage paramImageDelegate) {
        super(paramImageDelegate);
        Emulator.getEmulator().getLogStream().println("[mmpp] GraphicsX");
    }

    public Image capture(int x, int y, int width, int height) {
        Emulator.getEmulator().getLogStream().println("[mmpp] capture");
        if (width <= 0 || height <= 0){
            throw new IllegalArgumentException("Width and height must be greater than 0.");
        }
        Image img = Image.createImage(width, height);
        Graphics g = img.getGraphics();
        if (x < 0 || y < 0 || x + width > this.getClipWidth() || y + height > this.getClipHeight()) {
            throw new IllegalArgumentException("Capture area is outside the clipping region.");
        }
        g.drawImage(img, -x, -y, Graphics.TOP | Graphics.LEFT);
        return img;
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
        Emulator.getEmulator().getLogStream().println("[mmpp] setAlpha");
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
        Emulator.getEmulator().getLogStream().println("[mmpp] setXORMode");
        this.xorMode = true;
        this.xorColor = RGB;
        this.setColor(RGB);
        this.setAlpha(DEFAULT_ALPHA);
    }
}
