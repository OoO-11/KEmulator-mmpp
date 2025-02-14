package com.xce.lcdui;

import emulator.Emulator;
import emulator.graphics2D.IImage;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Toolkit {
    public static final int BLACK = 0x000000;
    public static final int DK_GRAY = 0x444444;
    public static final int LT_GRAY = 0xCCCCCC;
    public static final int WHITE = 0xFFFFFF;
    public static final Font DEFAULT_FONT = Font.getDefaultFont();
    public static final int FONT_HEIGHT = 12;
    public static final int FONT_GAP = 2;
    public static int IMG_HEIGHT;
    public static final int MAX_CHARWIDTH = 10;
    public static boolean IS_KOREAN;
    public static int selected;
    public static String ext;
    public static Graphics graphics = new Graphics((IImage) Emulator.getEmulator().getScreen().getBackBufferImage());
    public static String iconsDir;
    public static com.xce.lcdui.MIDPRes MIDP_RES;

    public static int tranx = 0;
    public static int trany = 0;

    public Toolkit() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  init");
        // Constructor
    }

    public static Image createImage(String fileName) throws java.io.IOException {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  createImage");
        // Implementation here
        return null;
    }

    public static Image createExImage(String dirName, String fileName) throws java.io.IOException {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  createExImage");
        // Implementation here
        return null;
    }

    public static int splitString(String s, int maxWidth) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  splitString");
        // Implementation here
        return 0;
    }

    public static Image titleImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  titleImg");
        // Implementation here
        return null;
    }

    public static void setTitleImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setTitleImg");
        // Implementation here
    }

    public static Image buttonBackLeftImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  buttonBackLeftImg");
        // Implementation here
        return null;
    }

    public static void setButtonBackLeftImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setButtonBackLeftImg");
        // Implementation here
    }

    public static Image buttonBackRightImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  buttonBackRightImg");
        // Implementation here
        return null;
    }

    public static void setButtonBackRightImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setButtonBackRightImg");
        // Implementation here
    }

    public static Image screenIconImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  screenIconImg");
        // Implementation here
        return null;
    }

    public static void setScreenIconImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setScreenIconImg");
        // Implementation here
    }

    public static Image backIconImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  backIconImg");
        // Implementation here
        return null;
    }

    public static void setBackIconImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setBackIconImg");
        // Implementation here
    }

    public static Image cancelIconImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  cancelIconImg");
        // Implementation here
        return null;
    }

    public static void setCancelIconImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setCancelIconImg");
        // Implementation here
    }

    public static Image helpIconImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  helpIconImg");
        // Implementation here
        return null;
    }

    public static void setHelpIconImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setHelpIconImg");
        // Implementation here
    }

    public static Image okIconImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  okIconImg");
        // Implementation here
        return null;
    }

    public static void setOkIconImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setOkIconImg");
        // Implementation here
    }

    public static Image stopIconImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  stopIconImg");
        // Implementation here
        return null;
    }

    public static void setStopIconImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setStopIconImg");
        // Implementation here
    }

    public static Image exitIconImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  exitIconImg");
        // Implementation here
        return null;
    }

    public static void setExitIconImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setExitIconImg");
        // Implementation here
    }

    public static Image itemIconImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  itemIconImg");
        // Implementation here
        return null;
    }

    public static void setItemIconImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setItemIconImg");
        // Implementation here
    }

    public static Image menuIconImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  menuIconImg");
        // Implementation here
        return null;
    }

    public static void setMenuIconImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setMenuIconImg");
        // Implementation here
    }

    public static Image ueimImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  ueimImg");
        // Implementation here
        return null;
    }

    public static void setUEimImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setUEimImg");
        // Implementation here
    }

    public static Image leimImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  leimImg");
        // Implementation here
        return null;
    }

    public static void setLEimImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setLEimImg");
        // Implementation here
    }

    public static Image kimImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  kimImg");
        // Implementation here
        return null;
    }

    public static void setKimImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setKimImg");
        // Implementation here
    }

    public static Image simImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  simImg");
        // Implementation here
        return null;
    }

    public static void setSimImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setSimImg");
        // Implementation here
    }

    public static Image nimImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  nimImg");
        // Implementation here
        return null;
    }

    public static void setNimImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setNimImg");
        // Implementation here
    }

    public static Image imHintImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  imHintImg");
        // Implementation here
        return null;
    }

    public static void setIMHintImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setIMHintImg");
        // Implementation here
    }

    public static Image sExclusive() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  sExclusive");
        // Implementation here
        return null;
    }

    public static void setSExclusive(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setSExclusive");
        // Implementation here
    }

    public static Image uExclusive() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  uExclusive");
        // Implementation here
        return null;
    }

    public static void setUExclusive(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setUExclusive");
        // Implementation here
    }

    public static Image sMultiple() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  sMultiple");
        // Implementation here
        return null;
    }

    public static void setSMultiple(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setSMultiple");
        // Implementation here
    }

    public static Image uMultiple() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  uMultiple");
        // Implementation here
        return null;
    }

    public static void setUMultiple(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setUMultiple");
        // Implementation here
    }

    public static Image sBackImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  sBackImg");
        // Implementation here
        return null;
    }

    public static void setSBackImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setSBackImg");
        // Implementation here
    }

    public static Image gBackImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  gBackImg");
        // Implementation here
        return null;
    }

    public static void setGBackImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setGBackImg");
        // Implementation here
    }

    public static Image gForeImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  gForeImg");
        // Implementation here
        return null;
    }

    public static void setGForeImg(Image img) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  setGForeImg");
        // Implementation here
    }

    public static Image appImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  appImg");
        // Implementation here
        return null;
    }

    public static Image scrollImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  scrollImg");
        // Implementation here
        return null;
    }

    public static Image castleImg() {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  castleImg");
        // Implementation here
        return null;
    }

    public static void paintPopup(String msg1, String msg2) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  paintPopup");
        // Implementation here
    }

    public static void paintPopup(String msg1, String msg2, boolean button) {
        Emulator.getEmulator().getLogStream().println("[xce.lcdui.Toolkit]  paintPopup");
        // Implementation here
    }
}

