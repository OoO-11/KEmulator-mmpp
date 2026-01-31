package mmpp.lang;

import emulator.Emulator;

public final class MathFP {

    // 디버그 플래그
    private static final boolean DEBUG = true;

    // 고정소수점 표현: 20비트 정수 + 12비트 소수
    private static final int FRACTION_BITS = 12;
    private static final int FRACTION_MASK = (1 << FRACTION_BITS) - 1;
    private static final int ONE = 1 << FRACTION_BITS;

    // 상수 정의
    public static final int PI = (int)(Math.PI * ONE);
    public static final int E  = (int)(Math.E * ONE);
    public static final int MAX_VALUE_INT = (1 << 19) - 1;
    public static final int MIN_VALUE_INT = -(1 << 19);
    public static final int MAX_VALUE = MAX_VALUE_INT << FRACTION_BITS;
    public static final int MIN_VALUE = MIN_VALUE_INT << FRACTION_BITS;

    // 인스턴스화 방지
    private MathFP() {}

    // 변환 관련
    public static int parseFP(int value) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] parseFP input: value=" + value);
        if (value > MAX_VALUE_INT || value < MIN_VALUE_INT) {
            throw new NumberFormatException("Out of range");
        }
        int result = value << FRACTION_BITS;
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] parseFP output: " + result);
        return result;
    }

    public static int parseFP(String s) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] parseFP input: s=" + s);
        double d = Double.parseDouble(s);
        int fp = (int)(d * ONE);
        if (fp > MAX_VALUE || fp < MIN_VALUE) {
            throw new NumberFormatException("Out of range");
        }
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] parseFP output: " + fp);
        return fp;
    }

    public static int toInt(int fp) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] toInt input: fp=" + fp);
        int result = (fp + (ONE >> 1)) >> FRACTION_BITS; // 반올림
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] toInt output: " + result);
        return result;
    }

    public static String toString(int fp) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] toString input: fp=" + fp);
        String result = String.valueOf((double)fp / ONE);
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] toString output: " + result);
        return result;
    }

    // 산술 연산
    public static int add(int a, int b) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] add input: a=" + a + ", b=" + b);
        int result = a + b;
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] add output: " + result);
        return result;
    }

    public static int sub(int a, int b) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] sub input: a=" + a + ", b=" + b);
        int result = a - b;
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] sub output: " + result);
        return result;
    }

    public static int multiply(int a, int b) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] multiply input: a=" + a + ", b=" + b);
        long result = ((long)a * (long)b) >> FRACTION_BITS;
        if (result > MAX_VALUE || result < MIN_VALUE) {
            throw new ArithmeticException("Overflow");
        }
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] multiply output: " + (int)result);
        return (int)result;
    }

    public static int divide(int a, int b) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] divide input: a=" + a + ", b=" + b);
        if (b == 0) throw new ArithmeticException("Division by zero");
        long result = ((long)a << FRACTION_BITS) / b;
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] divide output: " + (int)result);
        return (int)result;
    }

    public static int abs(int a) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] abs input: a=" + a);
        int result = (a < 0) ? -a : a;
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] abs output: " + result);
        return result;
    }

    public static int round(int a) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] round input: a=" + a);
        int result = toInt(a) << FRACTION_BITS;
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] round output: " + result);
        return result;
    }

    // 고급 연산 (간단 구현 예시)
    public static int sqrt(int i) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] sqrt input: i=" + i);
        if (i < 0) {
            throw new IllegalArgumentException("Negative input");
        }
        double value = (double)i / ONE;
        double sqrtVal = Math.sqrt(value);
        int fpResult = (int)(sqrtVal * ONE);
        if (fpResult > MAX_VALUE) {
            throw new ArithmeticException("Overflow");
        }
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] sqrt output: " + fpResult);
        return fpResult;
    }

    public static int log(int a) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] log input: a=" + a);
        if (a <= 0) throw new IllegalArgumentException("Non-positive input");
        double value = (double)a / ONE;
        double logVal = Math.log(value);
        int fpResult = (int)Math.round(logVal * ONE);
        if (fpResult > MAX_VALUE || fpResult < MIN_VALUE) {
            throw new ArithmeticException("Overflow in log");
        }
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] log output: " + fpResult);
        return fpResult;
    }

    public static int exp(int a) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] exp input: a=" + a);
        double value = (double)a / ONE;
        double expVal = Math.exp(value);
        int fpResult = (int)Math.round(expVal * ONE);
        if (fpResult > MAX_VALUE) throw new ArithmeticException("Overflow in exp");
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] exp output: " + fpResult);
        return fpResult;
    }

    public static int pow(int base, int exp) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] pow input: base=" + base + ", exp=" + exp);
        double b = (double)base / ONE;
        double e = (double)exp / ONE;
        if (b < 0 && e != Math.floor(e)) {
            throw new IllegalArgumentException("Negative base with fractional exponent");
        }
        double powVal = Math.pow(b, e);
        int fpResult = (int)Math.round(powVal * ONE);
        if (fpResult > MAX_VALUE || fpResult < MIN_VALUE) {
            throw new ArithmeticException("Overflow in pow");
        }
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] pow output: " + fpResult);
        return fpResult;
    }

    // 삼각 함수
    public static int sin(int r) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] sin input: r=" + r);
        int result = (int)(Math.sin((double)r / ONE) * ONE);
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] sin output: " + result);
        return result;
    }

    public static int cos(int r) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] cos input: r=" + r);
        int result = (int)(Math.cos((double)r / ONE) * ONE);
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] cos output: " + result);
        return result;
    }

    public static int tan(int r) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] tan input: r=" + r);
        int result = (int)(Math.tan((double)r / ONE) * ONE);
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] tan output: " + result);
        return result;
    }

    public static int asin(int r) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] asin input: r=" + r);
        double val = (double)r / ONE;
        if (val < -1.0 || val > 1.0) throw new IllegalArgumentException("asin input out of range");
        int result = (int)Math.round(Math.asin(val) * ONE);
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] asin output: " + result);
        return result;
    }

    public static int acos(int r) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] acos input: r=" + r);
        double val = (double)r / ONE;
        if (val < -1.0 || val > 1.0) throw new IllegalArgumentException("acos input out of range");
        int result = (int)Math.round(Math.acos(val) * ONE);
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] acos output: " + result);
        return result;
    }

    public static int atan(int r) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] atan input: r=" + r);
        double val = (double)r / ONE;
        int result = (int)Math.round(Math.atan(val) * ONE);
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] atan output: " + result);
        return result;
    }

    // max/min
    public static int max(int a, int b) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] max input: a=" + a + ", b=" + b);
        int result = Math.max(a, b);
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] max output: " + result);
        return result;
    }

    public static int min(int a, int b) {
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] min input: a=" + a + ", b=" + b);
        int result = Math.min(a, b);
        if (DEBUG) Emulator.getEmulator().getLogStream().println("[mmpp] min output: " + result);
        return result;
    }
}
