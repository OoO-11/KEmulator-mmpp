package com.skt.m;

import emulator.Emulator;

/*
* usage example
* MathFP.toStringLF(MathFP.add( MathFP.parseFPString("2.112"),MathFP.parseFPString("3.145"))));
 */

public final class MathFP {
    public static final long PI = Double.doubleToLongBits(Math.PI);
    public static final long E = Double.doubleToLongBits(Math.E);
    public static final long MAX_VALUE = Double.doubleToLongBits(Double.MAX_VALUE);
    public static final long MIN_VALUE = Double.doubleToLongBits(-Double.MAX_VALUE);

    private MathFP() {
    }

    public static long parseFP(long f) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] parseFP " + f);
        return Double.doubleToLongBits((double) f);
    }

    public static long parseFPString(String s) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] parseFPString " + s);
        double d = Double.parseDouble(s);
        return Double.doubleToLongBits(d);
    }

    public static long toLong(long f) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] toLong " + f);
        return (long) Double.longBitsToDouble(f);
    }

    public static String toStringLF(long f, int precision) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] toStringLF " + f + ", precision=" + precision);
        double d = Double.longBitsToDouble(f);
        return String.format("%." + precision + "f", d);
    }

    public static String toStringE(long f) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] toStringE " + f);
        double d = Double.longBitsToDouble(f);
        return String.format("%e", d);
    }

    public static long add(long a, long b) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] add " + a + ", " + b);
        return Double.doubleToLongBits(Double.longBitsToDouble(a) + Double.longBitsToDouble(b));
    }

    public static long sub(long a, long b) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] sub " + a + ", " + b);
        return Double.doubleToLongBits(Double.longBitsToDouble(a) - Double.longBitsToDouble(b));
    }

    public static long multiply(long a, long b) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] multiply " + a + ", " + b);
        return Double.doubleToLongBits(Double.longBitsToDouble(a) * Double.longBitsToDouble(b));
    }

    public static long divide(long a, long b) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] divide " + a + ", " + b);
        return Double.doubleToLongBits(Double.longBitsToDouble(a) / Double.longBitsToDouble(b));
    }

    public static long abs(long f) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] abs " + f);
        return Double.doubleToLongBits(Math.abs(Double.longBitsToDouble(f)));
    }

    public static long round(long f) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] round " + f);
        return Double.doubleToLongBits(Math.round(Double.longBitsToDouble(f)));
    }

    public static long sqrt(long f) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] sqrt " + f);
        return Double.doubleToLongBits(Math.sqrt(Double.longBitsToDouble(f)));
    }

    public static long pow(long b, long e) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] pow base=" + b + ", exp=" + e);
        return Double.doubleToLongBits(Math.pow(Double.longBitsToDouble(b), Double.longBitsToDouble(e)));
    }

    public static long log(long f) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] log " + f);
        return Double.doubleToLongBits(Math.log(Double.longBitsToDouble(f)));
    }

    public static long exp(long f) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] exp " + f);
        return Double.doubleToLongBits(Math.exp(Double.longBitsToDouble(f)));
    }

    public static long sin(long r) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] sin " + r);
        return Double.doubleToLongBits(Math.sin(Double.longBitsToDouble(r)));
    }

    public static long cos(long r) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] cos " + r);
        return Double.doubleToLongBits(Math.cos(Double.longBitsToDouble(r)));
    }

    public static long tan(long r) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] tan " + r);
        return Double.doubleToLongBits(Math.tan(Double.longBitsToDouble(r)));
    }

    public static long asin(long r) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] asin " + r);
        return Double.doubleToLongBits(Math.asin(Double.longBitsToDouble(r)));
    }

    public static long acos(long r) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] acos " + r);
        return Double.doubleToLongBits(Math.acos(Double.longBitsToDouble(r)));
    }

    public static long atan(long r) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] atan " + r);
        return Double.doubleToLongBits(Math.atan(Double.longBitsToDouble(r)));
    }

    // max/min
    public static long max(long a, long b) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] max " + a + ", " + b);
        return Double.doubleToLongBits(Math.max(Double.longBitsToDouble(a), Double.longBitsToDouble(b)));
    }

    public static long min(long a, long b) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MathFP] min " + a + ", " + b);
        return Double.doubleToLongBits(Math.min(Double.longBitsToDouble(a), Double.longBitsToDouble(b)));
    }

}