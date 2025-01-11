package mmpp.microedition.lcdui;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import  javax.microedition.lcdui.TextField;

import emulator.Emulator;

public class TextFieldX extends TextField{
    public static final int IM_NONE = 0;

    public static final int IM_ROMAN_CAPS = 1;

    public static final int IM_ROMAN_SMALL = 2;

    public static final int IM_NUMERIC = 4;

    public static final int IM_SYMBOL = 8;

    public static final int IM_KOREAN = 32;

    public TextFieldX(String label, String text, int maxSize, int constraints) {
        super(label, text, maxSize, constraints);
        Emulator.getEmulator().getLogStream().println("[mmpp] TextFieldX");
    }

    public void delete(int paramInt1, int paramInt2) {
        Emulator.getEmulator().getLogStream().println("[mmpp] delete");
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public int getCaretPosition() {
        Emulator.getEmulator().getLogStream().println("[mmpp] getCaretPosition");
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public int getChars(char[] paramArrayOfchar) {
        Emulator.getEmulator().getLogStream().println("[mmpp] getChars");
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public int getConstraints() {
        Emulator.getEmulator().getLogStream().println("[mmpp] getConstraints");
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public Font getFont() {
        Emulator.getEmulator().getLogStream().println("[mmpp] getFont");
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public int getHeight() {
        Emulator.getEmulator().getLogStream().println("[mmpp] getHeight");
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public int getInputMode() {
        Emulator.getEmulator().getLogStream().println("[mmpp] getInputMode");
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public int getMaxRow() {
        Emulator.getEmulator().getLogStream().println("[mmpp] getMaxRow");
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public int getMaxSize() {
        Emulator.getEmulator().getLogStream().println("[mmpp] getMaxSize");
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public Canvas getOwner() {
        Emulator.getEmulator().getLogStream().println("[mmpp] getOwner");
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public String getString() {
        Emulator.getEmulator().getLogStream().println("[mmpp] getString");
        return new String("");
    }

    public int getWidth() {
        Emulator.getEmulator().getLogStream().println("[mmpp] getWidth");
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public boolean hasFocus() {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void insert(String paramString, int paramInt) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void insert(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void keyPressed(int paramInt) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void keyReleased(int paramInt) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void keyRepeated(int paramInt) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public int nextInputMode() {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void paint(Graphics paramGraphics) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void setChars(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void setConstraints(int paramInt) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void setFocus(boolean paramBoolean) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void setFont(Font paramFont) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void setMaxRow(int paramInt) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public int setMaxSize(int paramInt) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void setOwner(Canvas paramCanvas) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void setString(String paramString) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void setWidth(int paramInt) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public int size() {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }
}
