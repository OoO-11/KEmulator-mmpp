/*
original code from https://github.com/usernameak/sktemu
 */
package com.xce.lcdui;

public interface InputMethod {
    void keyPress(IInputTarget target, int key);
    void startNewCharacter(IInputTarget target);
}
