package com.skt.m;

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
        return f * SCALE_FACTOR;
    }

    /**
     * 문자열을 고정 소수점(long)으로 변환
     */
    public static long parseFPString(String s) {
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
        boolean negative = f < 0;
        if (negative) {
            f = -f;
        }

        long integerPart = f / SCALE_FACTOR;
        long fractionalPart = ((f % SCALE_FACTOR) * (long) Math.pow(10, precision)) / SCALE_FACTOR;

        String result = integerPart + "." + String.format("%0" + precision + "d", fractionalPart);
        return negative ? "-" + result : result;
    }

    /**
     * 고정 소수점(long) 덧셈 연산
     */
    public static long add(long a, long b) {
        return a + b;
    }
}
