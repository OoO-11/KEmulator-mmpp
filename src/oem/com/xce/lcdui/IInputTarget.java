/*
original code from https://github.com/usernameak/sktemu
 */
package com.xce.lcdui;

public interface IInputTarget {
    void generateInputCharacter(char ch);
    void discardInputCharacter();
}