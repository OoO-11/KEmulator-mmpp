package mmpp.microedition.lcdui;

import javax.microedition.lcdui.*;

import emulator.Emulator;

public class TextFieldX extends TextField{
    public static final int IM_NONE = 0;

    public static final int IM_ROMAN_CAPS = 1;

    public static final int IM_ROMAN_SMALL = 2;

    public static final int IM_NUMERIC = 4;

    public static final int IM_SYMBOL = 8;

    public static final int IM_KOREAN = 32;

    private static int CURMODE = 2;
    private static int WIDTH = 5;
    private static int MAXROW = 1;

    public TextFieldX(String label, String text, int maxSize, int constraints) {
        super(label, text, maxSize, constraints);
        Emulator.getEmulator().getLogStream().println("[mmpp] TextFieldX");
    }

//    public void delete(int offset, int length) {
//        Emulator.getEmulator().getLogStream().println("[mmpp] delete");
//        super.delete(offset, length);
//    }

//    public int getCaretPosition() {
//        Emulator.getEmulator().getLogStream().println("[mmpp] getCaretPosition");
//        super.getCaretPosition();
//    }

//    public int getChars(char[] data) {
//        Emulator.getEmulator().getLogStream().println("[mmpp] getChars");
//        String content = getString();
//        if (data.length < content.length()) {
//            throw new ArrayIndexOutOfBoundsException("The length of the data array is shorter than the content of TextFieldX.");
//        }
//        content.getChars(0, content.length(), data, 0);
//        return content.length();
//    }

//    public int getConstraints() {
//        Emulator.getEmulator().getLogStream().println("[mmpp] getConstraints");
//        return super.getConstraints();
//    }

    public Font getFont() {
        Emulator.getEmulator().getLogStream().println("[mmpp] getFont");
        return Font.getFont(0, 1, 8);
    }

    public int getHeight() {
        Emulator.getEmulator().getLogStream().println("[mmpp] getHeight");
        return getMinimumHeight();
    }

    public int getInputMode() {
        Emulator.getEmulator().getLogStream().println("[mmpp] getInputMode");
        return CURMODE;
    }

    public int getMaxRow() {
        Emulator.getEmulator().getLogStream().println("[mmpp] getMaxRow");
        return MAXROW;
    }

//    public int getMaxSize() {
//        Emulator.getEmulator().getLogStream().println("[mmpp] getMaxSize");
//        return super.getMaxSize();
//    }

    public Canvas getOwner() {
        Emulator.getEmulator().getLogStream().println("[mmpp] getOwner");
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

//    public String getString() {
//        Emulator.getEmulator().getLogStream().println("[mmpp] getString");
//        return super.getString();
//    }

    public int getWidth() {
        Emulator.getEmulator().getLogStream().println("[mmpp] getWidth");
        return  getMinimumWidth();
    }

    public boolean hasFocus() {
        return focused;
    }

//    public void insert(String src, int position) {
//        throw new UnsupportedOperationException("This method is not yet implemented.");
//    }

//    public void insert(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3) {
//        throw new UnsupportedOperationException("This method is not yet implemented.");
//    }

    public void keyPressed(int keyCode) {
        if(!focused){
            return;
        }
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void keyReleased(int keyCode) {
        if(!focused){
            return;
        }
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void keyRepeated(int keyCode) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public int nextInputMode() {
        if(!focused){
            return IM_NONE;
        }
        return CURMODE;
//        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void paint(Graphics g) {
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

    public void setMaxRow(int maxRow) {
        MAXROW = maxRow;
    }

//    public int setMaxSize(int paramInt) {
//        throw new UnsupportedOperationException("This method is not yet implemented.");
//    }

    public void setOwner(Canvas paramCanvas) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

//    public void setString(String paramString) {
//        throw new UnsupportedOperationException("This method is not yet implemented.");
//    }

    public void setWidth(int width) {
        WIDTH = width;
    }

//    public int size() {
//        throw new UnsupportedOperationException("This method is not yet implemented.");
//    }
}
