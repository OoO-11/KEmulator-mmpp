package com.skt.m;

import emulator.Emulator;

/*
* usage example
* MathFP.toStringLF(MathFP.add( MathFP.parseFPString("2.112"),MathFP.parseFPString("3.145"))));
 */

public final class MathFP {
    private static final int SCALE_BITS = 16; // 소수부 비트 수
    private static final long SCALE_FACTOR = 1L << SCALE_BITS; // 2^16 = 65536

    // 상수 정의
    public static final long E = parseFPString("2.718281828459045");
    public static final long PI = parseFPString("3.141592653589793");
    public static final long MAX_VALUE = (1L << 62) - 1; // 62비트 사용 (안전 범위)
    public static final long MIN_VALUE = -(1L << 62) + 1;

    private MathFP() {} // 정적 클래스, 인스턴스화 방지

    /**
     * 정수를 고정 소수점(long)으로 변환
     */
    public static long parseFP(long f) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] parseFP "+f);
        return f * SCALE_FACTOR;
    }

    /**
     * 문자열을 고정 소수점(long)으로 변환
     */
    public static long parseFPString(String s) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] parseFPString "+s);
        boolean negative = s.startsWith("-");
        if (negative) {
            s = s.substring(1);
        }

        int dotIndex = s.indexOf(".");
        long integerPart = 0;
        long fractionalPart = 0;

        if (dotIndex >= 0) {
            integerPart = Long.parseLong(s.substring(0, dotIndex));
            String fracString = s.substring(dotIndex + 1);
            fractionalPart = Long.parseLong(fracString);
            fractionalPart = (fractionalPart * SCALE_FACTOR) / (long) Math.pow(10, fracString.length());
        } else {
            integerPart = Long.parseLong(s);
        }

        long result = (integerPart * SCALE_FACTOR) + fractionalPart;
        return negative ? -result : result;
    }

    /**
     * 고정 소수점(long)을 10진 표기 문자열로 변환
     */
    public static String toStringLF(long f, int precision) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] toStringLF "+f+" precision"+precision);
        boolean negative = f < 0;
        if (negative) {
            f = -f;
        }

        long integerPart = f / SCALE_FACTOR;
        long fractionalPart = ((f % SCALE_FACTOR) * (long) Math.pow(10, precision)) / SCALE_FACTOR;

        String result = integerPart + "." + String.format("%0" + precision + "d", fractionalPart);
        return negative ? "-" + result : result;
    }

    public static long toLong(long f) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] toLong "+f);
        return f / SCALE_FACTOR;
    }

    public static String toStringE(long f) {
        Emulator.getEmulator().getLogStream().println("[skt.m.toStringE] toLong "+f);
        double value = f / (double) SCALE_FACTOR;
        return String.format("%e", value);
    }

    /**
     * 고정 소수점(long) 덧셈 연산
     */
    public static long add(long a, long b) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] add");
        return a + b;
    }

    public static long sub(long a, long b) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] sub");
        return a - b;
    }

    public static long divide(long a, long b){
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] divide");
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return (a * SCALE_FACTOR) / b;
    }

    public static long multiply(long a, long b) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] multiply");
        return (a * b) / SCALE_FACTOR;
    }

    public static long sin(long r) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] sin");
        double radian = r / (double) SCALE_FACTOR; // 고정소수점 -> 부동소수점 변환
        return (long) (Math.sin(radian) * SCALE_FACTOR); // 결과를 고정소수점으로 변환
    }

    public static long cos(long r) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] cos");
        double radian = r / (double) SCALE_FACTOR;
        return (long) (Math.cos(radian) * SCALE_FACTOR);
    }

    public static long tan(long r) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] tan");
        double radian = r / (double) SCALE_FACTOR;
        return (long) (Math.tan(radian) * SCALE_FACTOR);
    }

    public static long sqrt(long f) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] sqrt");
        if (f < 0) {
            throw new IllegalArgumentException("Negative input: " + f);
        }
        double value = f / (double) SCALE_FACTOR; // 고정소수점 -> 부동소수점 변환
        return (long) (Math.sqrt(value) * SCALE_FACTOR); // 결과를 고정소수점으로 변환
    }
    public static long abs(long f) {
        return (f < 0) ? -f : f;
    }

    public static long acos(long r) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] acos");
        double value = r / (double) SCALE_FACTOR;
        return (long) (Math.acos(value) * SCALE_FACTOR);
    }

    public static long asin(long r) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] asin");
        double value = r / (double) SCALE_FACTOR;
        return (long) (Math.asin(value) * SCALE_FACTOR);
    }

    public static long atan(long r) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] atan");
        double value = r / (double) SCALE_FACTOR;
        return (long) (Math.atan(value) * SCALE_FACTOR);
    }

    public static long exp(long f) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] exp");
        double value = f / (double) SCALE_FACTOR;
        return (long) (Math.exp(value) * SCALE_FACTOR);
    }

    public static long log(long f) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] log");
        if (f <= 0) {
            throw new IllegalArgumentException("Log input must be positive");
        }
        double value = f / (double) SCALE_FACTOR;
        return (long) (Math.log(value) * SCALE_FACTOR);
    }

    public static long max(long a, long b) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] max");
        return (a > b) ? a : b;
    }

    public static long min(long a, long b) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] min");
        return (a < b) ? a : b;
    }

    public static long pow(long base, long exp) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] pow");
        double baseValue = base / (double) SCALE_FACTOR;
        double expValue = exp / (double) SCALE_FACTOR;
        return (long) (Math.pow(baseValue, expValue) * SCALE_FACTOR);
    }

    public static long round(long f) {
        Emulator.getEmulator().getLogStream().println("[skt.m.MATHFP] round");
        return (f + SCALE_FACTOR / 2) / SCALE_FACTOR * SCALE_FACTOR;
    }


}
