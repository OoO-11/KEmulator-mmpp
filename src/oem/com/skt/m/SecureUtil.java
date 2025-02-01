package com.skt.m;

//import com.xce.lcdui.Toolkit;
//import com.xce.lcdui.XDisplay;
//import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

public class SecureUtil {
    int[] a;
    byte[] b;
    long d;
    int[] c = new int[4];

    public static String isValid(String var1, String var4) {
        SecureUtil var6 = new SecureUtil();
        byte[] var7 = (var1 + var4 + "a0a535ef35b").getBytes();
        var6.b(var7, 0, var7.length);
        byte[] var8 = new byte[16];
        var6.a(var8);
        String var9 = var6.b(var8);
        Object var10 = null;
        return var9;
//        return var5.equals(var9);
    }

    public static String isValid(String var1, String var4, String var5) {
        SecureUtil var6 = new SecureUtil();
        byte[] var7 = (var1 + var4 + "a0a535ef35b" + var5).getBytes();
        var6.b(var7, 0, var7.length);
        byte[] var8 = new byte[16];
        var6.a(var8);
        String var9 = var6.b(var8);
        Object var10 = null;
        return var9;
//        return var5.equals(var9);
    }

    private String b(byte[] var1) {
        StringBuffer var3 = new StringBuffer();

        for(int var2 = 0; var2 < var1.length; ++var2) {
            if (var2 % 32 == 0 && var2 != 0) {
                var3.append("\n");
            }

            String var4 = Integer.toHexString(var1[var2]);
            if (var4.length() < 2) {
                var4 = "0" + var4;
            }

            if (var4.length() > 2) {
                var4 = var4.substring(var4.length() - 2);
            }

            var3.append(var4);
        }

        return var3.toString();
    }

    private void a(byte[] var1, int var2, int var3) {
        var1[var2 + 0] = (byte)(var3 & 255);
        var1[var2 + 1] = (byte)(var3 >> 8 & 255);
        var1[var2 + 2] = (byte)(var3 >> 16 & 255);
        var1[var2 + 3] = (byte)(var3 >> 24 & 255);
    }

    private int a(byte[] var1, int var2) {
        return var1[var2 + 0] & 255 | (var1[var2 + 1] & 255) << 8 | (var1[var2 + 2] & 255) << 16 | (var1[var2 + 3] & 255) << 24;
    }

    private void b() {
        int[] var2 = new int[16];

        for(int var1 = 0; var1 < 16; ++var1) {
            var2[var1] = this.a(this.b, 4 * var1);
        }

        int var3 = this.c[0];
        int var4 = this.c[1];
        int var5 = this.c[2];
        int var6 = this.c[3];
        var3 = this.a(0, var3, var4, var5, var6, var2[0] + -680876936, 7);
        var6 = this.a(0, var6, var3, var4, var5, var2[1] + -389564586, 12);
        var5 = this.a(0, var5, var6, var3, var4, var2[2] + 606105819, 17);
        var4 = this.a(0, var4, var5, var6, var3, var2[3] + -1044525330, 22);
        var3 = this.a(0, var3, var4, var5, var6, var2[4] + -176418897, 7);
        var6 = this.a(0, var6, var3, var4, var5, var2[5] + 1200080426, 12);
        var5 = this.a(0, var5, var6, var3, var4, var2[6] + -1473231341, 17);
        var4 = this.a(0, var4, var5, var6, var3, var2[7] + -45705983, 22);
        var3 = this.a(0, var3, var4, var5, var6, var2[8] + 1770035416, 7);
        var6 = this.a(0, var6, var3, var4, var5, var2[9] + -1958414417, 12);
        var5 = this.a(0, var5, var6, var3, var4, var2[10] + -42063, 17);
        var4 = this.a(0, var4, var5, var6, var3, var2[11] + -1990404162, 22);
        var3 = this.a(0, var3, var4, var5, var6, var2[12] + 1804603682, 7);
        var6 = this.a(0, var6, var3, var4, var5, var2[13] + -40341101, 12);
        var5 = this.a(0, var5, var6, var3, var4, var2[14] + -1502002290, 17);
        var4 = this.a(0, var4, var5, var6, var3, var2[15] + 1236535329, 22);
        var3 = this.a(1, var3, var4, var5, var6, var2[1] + -165796510, 5);
        var6 = this.a(1, var6, var3, var4, var5, var2[6] + -1069501632, 9);
        var5 = this.a(1, var5, var6, var3, var4, var2[11] + 643717713, 14);
        var4 = this.a(1, var4, var5, var6, var3, var2[0] + -373897302, 20);
        var3 = this.a(1, var3, var4, var5, var6, var2[5] + -701558691, 5);
        var6 = this.a(1, var6, var3, var4, var5, var2[10] + 38016083, 9);
        var5 = this.a(1, var5, var6, var3, var4, var2[15] + -660478335, 14);
        var4 = this.a(1, var4, var5, var6, var3, var2[4] + -405537848, 20);
        var3 = this.a(1, var3, var4, var5, var6, var2[9] + 568446438, 5);
        var6 = this.a(1, var6, var3, var4, var5, var2[14] + -1019803690, 9);
        var5 = this.a(1, var5, var6, var3, var4, var2[3] + -187363961, 14);
        var4 = this.a(1, var4, var5, var6, var3, var2[8] + 1163531501, 20);
        var3 = this.a(1, var3, var4, var5, var6, var2[13] + -1444681467, 5);
        var6 = this.a(1, var6, var3, var4, var5, var2[2] + -51403784, 9);
        var5 = this.a(1, var5, var6, var3, var4, var2[7] + 1735328473, 14);
        var4 = this.a(1, var4, var5, var6, var3, var2[12] + -1926607734, 20);
        var3 = this.a(2, var3, var4, var5, var6, var2[5] + -378558, 4);
        var6 = this.a(2, var6, var3, var4, var5, var2[8] + -2022574463, 11);
        var5 = this.a(2, var5, var6, var3, var4, var2[11] + 1839030562, 16);
        var4 = this.a(2, var4, var5, var6, var3, var2[14] + -35309556, 23);
        var3 = this.a(2, var3, var4, var5, var6, var2[1] + -1530992060, 4);
        var6 = this.a(2, var6, var3, var4, var5, var2[4] + 1272893353, 11);
        var5 = this.a(2, var5, var6, var3, var4, var2[7] + -155497632, 16);
        var4 = this.a(2, var4, var5, var6, var3, var2[10] + -1094730640, 23);
        var3 = this.a(2, var3, var4, var5, var6, var2[13] + 681279174, 4);
        var6 = this.a(2, var6, var3, var4, var5, var2[0] + -358537222, 11);
        var5 = this.a(2, var5, var6, var3, var4, var2[3] + -722521979, 16);
        var4 = this.a(2, var4, var5, var6, var3, var2[6] + 76029189, 23);
        var3 = this.a(2, var3, var4, var5, var6, var2[9] + -640364487, 4);
        var6 = this.a(2, var6, var3, var4, var5, var2[12] + -421815835, 11);
        var5 = this.a(2, var5, var6, var3, var4, var2[15] + 530742520, 16);
        var4 = this.a(2, var4, var5, var6, var3, var2[2] + -995338651, 23);
        var3 = this.a(3, var3, var4, var5, var6, var2[0] + -198630844, 6);
        var6 = this.a(3, var6, var3, var4, var5, var2[7] + 1126891415, 10);
        var5 = this.a(3, var5, var6, var3, var4, var2[14] + -1416354905, 15);
        var4 = this.a(3, var4, var5, var6, var3, var2[5] + -57434055, 21);
        var3 = this.a(3, var3, var4, var5, var6, var2[12] + 1700485571, 6);
        var6 = this.a(3, var6, var3, var4, var5, var2[3] + -1894986606, 10);
        var5 = this.a(3, var5, var6, var3, var4, var2[10] + -1051523, 15);
        var4 = this.a(3, var4, var5, var6, var3, var2[1] + -2054922799, 21);
        var3 = this.a(3, var3, var4, var5, var6, var2[8] + 1873313359, 6);
        var6 = this.a(3, var6, var3, var4, var5, var2[15] + -30611744, 10);
        var5 = this.a(3, var5, var6, var3, var4, var2[6] + -1560198380, 15);
        var4 = this.a(3, var4, var5, var6, var3, var2[13] + 1309151649, 21);
        var3 = this.a(3, var3, var4, var5, var6, var2[4] + -145523070, 6);
        var6 = this.a(3, var6, var3, var4, var5, var2[11] + -1120210379, 10);
        var5 = this.a(3, var5, var6, var3, var4, var2[2] + 718787259, 15);
        var4 = this.a(3, var4, var5, var6, var3, var2[9] + -343485551, 21);
        int[] var10000 = this.c;
        var10000[0] += var3;
        var10000 = this.c;
        var10000[1] += var4;
        var10000 = this.c;
        var10000[2] += var5;
        var10000 = this.c;
        var10000[3] += var6;
    }

    private int a(int var1, int var2, int var3, int var4) {
        switch (var1) {
            case 0:
                return var4 ^ var2 & (var3 ^ var4);
            case 1:
                return var3 ^ var4 & (var2 ^ var3);
            case 2:
                return var2 ^ var3 ^ var4;
            default:
                return var3 ^ (var2 | ~var4);
        }
    }

    private int a(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
        var2 += this.a(var1, var3, var4, var5) + var6;
        var2 = var2 << var7 | var2 >>> 32 - var7;
        var2 += var3;
        return var2;
    }

    private void a(int[] var1, int var2, int var3, int var4) {
        int var6 = var3 + var4;

        for(int var5 = var3; var5 < var6; ++var5) {
            var1[var5] = var2;
        }

    }

    private void a(int[] var1, int var2, int var3) {
        this.a((int[])var1, (int)0, var2, var3);
    }

    private void a(byte[] var1, byte var2, int var3, int var4) {
        int var6 = var3 + var4;

        for(int var5 = var3; var5 < var6; ++var5) {
            var1[var5] = var2;
        }

    }

    private void c(byte[] var1, int var2, int var3) {
        this.a((byte[])var1, (byte)0, var2, var3);
    }

    void a(byte[] var1) {
        int var2 = (int)(this.d >>> 3 & 63L);
        int var3 = var2 + 1;
        this.b[var2] = -128;
        var2 = 63 - var2;
        if (var2 < 8) {
            this.c(this.b, var3, var2);
            this.b();
            this.c(this.b, 0, 56);
        } else {
            this.c(this.b, var3, var2 - 8);
        }

        int var4 = (int)this.d;
        int var5 = (int)(this.d >>> 32);
        this.a((byte[])this.b, 56, var4);
        this.a((byte[])this.b, 60, var5);
        this.b();
        this.a((byte[])var1, 0, this.c[0]);
        this.a((byte[])var1, 4, this.c[1]);
        this.a((byte[])var1, 8, this.c[2]);
        this.a((byte[])var1, 12, this.c[3]);
        this.c(this.b, 0, this.b.length);
        this.a((int[])this.c, 0, this.c.length);
        this.d = 0L;
        this.a((int[])this.a, 0, this.a.length);
    }

    void b(byte[] var1, int var2, int var3) {
        int var5 = var3;
        int var4 = (int)this.d;
        this.d += (long)(var3 << 3);
        var4 = var4 >>> 3 & 63;
        if (var4 != 0) {
            int var6 = var4;
            var4 = 64 - var4;
            if (var3 < var4) {
                System.arraycopy(var1, var2, this.b, var6, var3);
                return;
            }

            System.arraycopy(var1, var2, this.b, var6, var4);
            this.b();
            var2 += var4;
            var5 = var3 - var4;
        }

        while(var5 >= 64) {
            System.arraycopy(var1, var2, this.b, 0, 64);
            this.b();
            var2 += 64;
            var5 -= 64;
        }

        System.arraycopy(var1, var2, this.b, 0, var5);
    }

    public SecureUtil() {
        this.c[0] = 1732584193;
        this.c[1] = -271733879;
        this.c[2] = -1732584194;
        this.c[3] = 271733878;
        this.d = 0L;
        this.b = new byte[64];
        this.a = new int[16];
    }
}
