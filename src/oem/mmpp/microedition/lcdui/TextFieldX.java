package mmpp.microedition.lcdui;

import javax.microedition.lcdui.*;

import com.xce.lcdui.HangulInputMethod;
import com.xce.lcdui.IInputTarget;
import com.xce.lcdui.InputMethod;
import emulator.Emulator;

public class TextFieldX implements IInputTarget {
    public static final int IM_NONE = 0;

    public static final int IM_ROMAN_CAPS = 1;

    public static final int IM_ROMAN_SMALL = 2;

    public static final int IM_NUMERIC = 4;

    public static final int IM_SYMBOL = 8;

    public static final int IM_KOREAN = 32;

    private static int CURMODE = 2;
    private static int WIDTH = 5;
    private static int MAXROW = 1;


    private Canvas cv;
    private boolean focus = false;
    private InputMethod ime = new HangulInputMethod();
    private int cursorPos = 0;

    private TextField textField;
    private String text;

    public TextFieldX(String label, String text, int maxSize, int constraints) {
        Emulator.getEmulator().getLogStream().println("[mmpp] TextFieldX");
        textField = new TextField(label, text, maxSize, constraints);
        this.text = text;
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
        return getFont().getHeight() + 4;
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
        return cv;
    }

    public String getString() {
        Emulator.getEmulator().getLogStream().println("[mmpp] getString");
        return text;
    }

    public int getWidth() {
        Emulator.getEmulator().getLogStream().println("[mmpp] getWidth");
        return WIDTH;
    }

    public boolean hasFocus() {
        return focus;
    }

//    public void insert(String src, int position) {
//        throw new UnsupportedOperationException("This method is not yet implemented.");
//    }

//    public void insert(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3) {
//        throw new UnsupportedOperationException("This method is not yet implemented.");
//    }

    public void keyPressed(int keyCode) {
        Emulator.getEmulator().getLogStream().println("[mmpp] keyPressed " + keyCode);
        if(focus){
            ime.keyPress(this, keyCode);
            System.out.println(textField.getString());
            return;
        }
    }

    public void keyReleased(int keyCode) {
        Emulator.getEmulator().getLogStream().println("[mmpp] keyReleased " + keyCode);
        return;
    }

    public void keyRepeated(int keyCode) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public int nextInputMode() {
        Emulator.getEmulator().getLogStream().println("[mmpp] nextInputMode");
        if(!focus){
            return IM_NONE;
        }
        return CURMODE;
//        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void paint(Graphics g) {
        Emulator.getEmulator().getLogStream().println("[mmpp] paint");
        // 입력된 텍스트 그리기
        g.setColor(0xFFFFFFFF);
        g.fillRect(0, 0, WIDTH, getHeight());
        g.setColor(0x000000);
        g.setFont(getFont());
        g.drawString(text, 0, 0, Graphics.TOP | Graphics.LEFT);

    }

    public void setChars(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void setConstraints(int paramInt) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public void setFocus(boolean paramBoolean) {
        focus = paramBoolean;
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
        cv = Emulator.getCanvas();
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

    public void inputChar(char key) {
        Emulator.getEmulator().getLogStream().println("[mmpp]  inputChar " + key);
        textField.insert(new String(new char[]{key}), textField.getCaretPosition());
        if(!focus){
            return;
        }
//        if (key == 8) {
//            if (cursorPos == 0) return;
//
//            text = text.substring(0, cursorPos - 1) + text.substring(cursorPos);
//            cursorPos--;
//            return;
//        }
        if (key == '\b') {
            if (cursorPos == 0) return;

            text = text.substring(0, cursorPos - 1) + text.substring(cursorPos);
            cursorPos--;
            return;
        }
        if(text.length() > textField.getMaxSize()){
            return;
        }
//        // TODO : input... kr / en / ...
        text = text.substring(0, cursorPos) + key + text.substring(cursorPos);
        cursorPos++;


    }

    @Override
    public void generateInputCharacter(char ch) {
        inputChar(ch);
    }

    @Override
    public void discardInputCharacter() {
        inputChar('\b');
    }
}
