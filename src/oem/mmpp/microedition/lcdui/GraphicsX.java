package mmpp.microedition.lcdui;

import com.sun.midp.lcdui.ImageDelegate;
import emulator.graphics2D.IImage;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import emulator.Emulator;

public class GraphicsX extends Graphics {
    public static int DEFAULT_ALPHA = 256;

    GraphicsX(IImage paramImageDelegate) {
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
    }

    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints){
        Emulator.getEmulator().getLogStream().println("[mmpp] drawPolyline");
    }

    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints){
        Emulator.getEmulator().getLogStream().println("[mmpp] fillPolygon");
    }

    public int getPixel(int x, int y) {
        Emulator.getEmulator().getLogStream().println("[mmpp] getPixel");
        return 0;
    }

    public void setAlpha(int alpha) {
        Emulator.getEmulator().getLogStream().println("[mmpp] setAlpha");
        if(alpha < 0 || alpha > 256){
            throw new IllegalArgumentException("alpha should be 0~256.");
        }
        DEFAULT_ALPHA = alpha;
    }

    public void setPaintMode() {
        Emulator.getEmulator().getLogStream().println("[mmpp] setPaintMode");
    }

    public void setPixel(int x, int y, int RGB) {
        Emulator.getEmulator().getLogStream().println("[mmpp] setPixel");
    }

    public void setXORMode(int RGB) {
        Emulator.getEmulator().getLogStream().println("[mmpp] setXORMode");
    }
}
