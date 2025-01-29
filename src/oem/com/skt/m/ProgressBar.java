package com.skt.m;

import emulator.Emulator;

public final class ProgressBar {
    private int value = 0;
    private int maxValue = 100;

    public ProgressBar(String title) {
        Emulator.getEmulator().getLogStream().println("[skt.m.ProgressBar] ProgressBar");
    }

    public void setValue(int value) {
        Emulator.getEmulator().getLogStream().println("[skt.m.ProgressBar] setValue");
        this.value = value;
    }

    public int getValue() {
        Emulator.getEmulator().getLogStream().println("[skt.m.ProgressBar] getValue");
        return value;
    }

    public void setMaxValue(int maxValue2) {
        Emulator.getEmulator().getLogStream().println("[skt.m.ProgressBar] setMaxValue");
        this.maxValue = maxValue2;
    }

    public int getMaxValue() {
        Emulator.getEmulator().getLogStream().println("[skt.m.ProgressBar] getMaxValue");
        return this.maxValue;
    }
}
