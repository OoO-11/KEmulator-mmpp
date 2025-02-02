package com.skt.m;

public final class SecureUtill2 {
    private int[] intarr;
    private byte[] bytes;
    private long longa;
    private int[] b = new int[4];
    private static int inta = -1;

    private static void a(int[] var0, int var1, int var2, int var3) {
        int var4 = var2 + var3;

        for(int var5 = var2; var5 < var4; ++var5) {
            var0[var5] = var1;
        }

    }

    private void a(int[] var1, int var2, int var3) {
        a((int[])var1, (int)0, var2, var3);
    }

    private static int a(int var0, int var1, int var2, int var3) {
        switch (var0) {
            case 0:
                return var3 ^ var1 & (var2 ^ var3);
            case 1:
                return var2 ^ var3 & (var1 ^ var2);
            case 2:
                return var1 ^ var2 ^ var3;
            default:
                return var2 ^ (var1 | ~var3);
        }
    }

    private static void a(byte[] var0, byte var1, int var2, int var3) {
        int var4 = var2 + var3;

        for(int var5 = var2; var5 < var4; ++var5) {
            var0[var5] = var1;
        }

    }

    private int a(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
        int var8;
        return ((var8 = var2 + a(var1, var3, var4, var5) + var6) << var7 | var8 >>> 32 - var7) + var3;
    }

    private void a(byte[] var1, int var2, int var3) {
        a((byte[])var1, (byte)0, var2, var3);
    }

    private void processData(byte[] var1) {
        int var2;
        int var3 = var2 = (int)(this.longa >>> 3 & 63L);
        this.bytes[var3++] = -128;
        if ((var2 = 63 - var2) < 8) {
            this.a(this.bytes, var3, var2);
            this.b();
            this.a((byte[])this.bytes, 0, 56);
        } else {
            this.a(this.bytes, var3, var2 - 8);
        }

        int var4 = (int)this.longa;
        int var5 = (int)(this.longa >>> 32);
        b(this.bytes, 56, var4);
        b(this.bytes, 60, var5);
        this.b();
        b(var1, 0, this.b[0]);
        b(var1, 4, this.b[1]);
        b(var1, 8, this.b[2]);
        b(var1, 12, this.b[3]);
        this.a((byte[])this.bytes, 0, this.bytes.length);
        this.a((int[])this.b, 0, this.b.length);
        this.longa = 0L;
        this.a((int[])this.intarr, 0, this.intarr.length);
    }

    private static String a(byte[] var0) {
        StringBuffer var1 = new StringBuffer();

        for(int var2 = 0; var2 < var0.length; ++var2) {
            if (var2 % 32 == 0 && var2 != 0) {
                var1.append("\n");
            }

            String var3;
            if ((var3 = Integer.toHexString(var0[var2])).length() < 2) {
                var3 = "0" + var3;
            }

            if (var3.length() > 2) {
                var3 = var3.substring(var3.length() - 2);
            }

            var1.append(var3);
        }

        return var1.toString();
    }

    private static void b(byte[] var0, int var1, int var2) {
        var0[var1 + 0] = (byte)(var2 & 255);
        var0[var1 + 1] = (byte)(var2 >> 8 & 255);
        var0[var1 + 2] = (byte)(var2 >> 16 & 255);
        var0[var1 + 3] = (byte)(var2 >> 24 & 255);
    }

    private static int a(byte[] var0, int var1) {
        return var0[var1 + 0] & 255 | (var0[var1 + 1] & 255) << 8 | (var0[var1 + 2] & 255) << 16 | (var0[var1 + 3] & 255) << 24;
    }

    private static String getMIN() {
        Object var0 = null;
        String var1;
        if (System.getProperty("com.xce.wipi.version") == null) {
            var1 = System.getProperty("m.MIN");
        } else if ((var1 = System.getProperty("MIN")).charAt(0) == '0') {
            var1 = var1.substring(3);
        } else {
            var1 = var1.substring(2);
        }

        if (var1.length() == 7) {
            var1 = "0" + var1;
        }

        return var1;
    }

    public static String isValid(String var8, String var16, String var14) {

        String var13 = "";

        try {
            inta = 4;
            int var6 = 0;
            SecureUtill2 var7 = new SecureUtill2();
            if (inta >= 3) {
                var8 = var8.substring(var8.length() - 6, var8.length());
            }

            byte[] var9 = (var8 + var16 + "a0a535ef35b" + var14).getBytes();
            var7.c(var9, 0, var9.length);
            byte[] var10 = new byte[16];
            var7.processData(var10);
            var13 = a(var10);
        } catch (Exception var11) {
            System.out.println("error");
        }
        return var13;
    }

    private void b() {
        int[] var1 = new int[16];

        for(int var2 = 0; var2 < 16; ++var2) {
            var1[var2] = a(this.bytes, 4 * var2);
        }

        int var6 = this.b[0];
        int var3 = this.b[1];
        int var4 = this.b[2];
        int var5 = this.b[3];
        var6 = this.a(0, var6, var3, var4, var5, var1[0] + -680876936, 7);
        var5 = this.a(0, var5, var6, var3, var4, var1[1] + -389564586, 12);
        var4 = this.a(0, var4, var5, var6, var3, var1[2] + 606105819, 17);
        var3 = this.a(0, var3, var4, var5, var6, var1[3] + -1044525330, 22);
        var6 = this.a(0, var6, var3, var4, var5, var1[4] + -176418897, 7);
        var5 = this.a(0, var5, var6, var3, var4, var1[5] + 1200080426, 12);
        var4 = this.a(0, var4, var5, var6, var3, var1[6] + -1473231341, 17);
        var3 = this.a(0, var3, var4, var5, var6, var1[7] + -45705983, 22);
        var6 = this.a(0, var6, var3, var4, var5, var1[8] + 1770035416, 7);
        var5 = this.a(0, var5, var6, var3, var4, var1[9] + -1958414417, 12);
        var4 = this.a(0, var4, var5, var6, var3, var1[10] + -42063, 17);
        var3 = this.a(0, var3, var4, var5, var6, var1[11] + -1990404162, 22);
        var6 = this.a(0, var6, var3, var4, var5, var1[12] + 1804603682, 7);
        var5 = this.a(0, var5, var6, var3, var4, var1[13] + -40341101, 12);
        var4 = this.a(0, var4, var5, var6, var3, var1[14] + -1502002290, 17);
        var3 = this.a(0, var3, var4, var5, var6, var1[15] + 1236535329, 22);
        var6 = this.a(1, var6, var3, var4, var5, var1[1] + -165796510, 5);
        var5 = this.a(1, var5, var6, var3, var4, var1[6] + -1069501632, 9);
        var4 = this.a(1, var4, var5, var6, var3, var1[11] + 643717713, 14);
        var3 = this.a(1, var3, var4, var5, var6, var1[0] + -373897302, 20);
        var6 = this.a(1, var6, var3, var4, var5, var1[5] + -701558691, 5);
        var5 = this.a(1, var5, var6, var3, var4, var1[10] + 38016083, 9);
        var4 = this.a(1, var4, var5, var6, var3, var1[15] + -660478335, 14);
        var3 = this.a(1, var3, var4, var5, var6, var1[4] + -405537848, 20);
        var6 = this.a(1, var6, var3, var4, var5, var1[9] + 568446438, 5);
        var5 = this.a(1, var5, var6, var3, var4, var1[14] + -1019803690, 9);
        var4 = this.a(1, var4, var5, var6, var3, var1[3] + -187363961, 14);
        var3 = this.a(1, var3, var4, var5, var6, var1[8] + 1163531501, 20);
        var6 = this.a(1, var6, var3, var4, var5, var1[13] + -1444681467, 5);
        var5 = this.a(1, var5, var6, var3, var4, var1[2] + -51403784, 9);
        var4 = this.a(1, var4, var5, var6, var3, var1[7] + 1735328473, 14);
        var3 = this.a(1, var3, var4, var5, var6, var1[12] + -1926607734, 20);
        var6 = this.a(2, var6, var3, var4, var5, var1[5] + -378558, 4);
        var5 = this.a(2, var5, var6, var3, var4, var1[8] + -2022574463, 11);
        var4 = this.a(2, var4, var5, var6, var3, var1[11] + 1839030562, 16);
        var3 = this.a(2, var3, var4, var5, var6, var1[14] + -35309556, 23);
        var6 = this.a(2, var6, var3, var4, var5, var1[1] + -1530992060, 4);
        var5 = this.a(2, var5, var6, var3, var4, var1[4] + 1272893353, 11);
        var4 = this.a(2, var4, var5, var6, var3, var1[7] + -155497632, 16);
        var3 = this.a(2, var3, var4, var5, var6, var1[10] + -1094730640, 23);
        var6 = this.a(2, var6, var3, var4, var5, var1[13] + 681279174, 4);
        var5 = this.a(2, var5, var6, var3, var4, var1[0] + -358537222, 11);
        var4 = this.a(2, var4, var5, var6, var3, var1[3] + -722521979, 16);
        var3 = this.a(2, var3, var4, var5, var6, var1[6] + 76029189, 23);
        var6 = this.a(2, var6, var3, var4, var5, var1[9] + -640364487, 4);
        var5 = this.a(2, var5, var6, var3, var4, var1[12] + -421815835, 11);
        var4 = this.a(2, var4, var5, var6, var3, var1[15] + 530742520, 16);
        var3 = this.a(2, var3, var4, var5, var6, var1[2] + -995338651, 23);
        var6 = this.a(3, var6, var3, var4, var5, var1[0] + -198630844, 6);
        var5 = this.a(3, var5, var6, var3, var4, var1[7] + 1126891415, 10);
        var4 = this.a(3, var4, var5, var6, var3, var1[14] + -1416354905, 15);
        var3 = this.a(3, var3, var4, var5, var6, var1[5] + -57434055, 21);
        var6 = this.a(3, var6, var3, var4, var5, var1[12] + 1700485571, 6);
        var5 = this.a(3, var5, var6, var3, var4, var1[3] + -1894986606, 10);
        var4 = this.a(3, var4, var5, var6, var3, var1[10] + -1051523, 15);
        var3 = this.a(3, var3, var4, var5, var6, var1[1] + -2054922799, 21);
        var6 = this.a(3, var6, var3, var4, var5, var1[8] + 1873313359, 6);
        var5 = this.a(3, var5, var6, var3, var4, var1[15] + -30611744, 10);
        var4 = this.a(3, var4, var5, var6, var3, var1[6] + -1560198380, 15);
        var3 = this.a(3, var3, var4, var5, var6, var1[13] + 1309151649, 21);
        var6 = this.a(3, var6, var3, var4, var5, var1[4] + -145523070, 6);
        var5 = this.a(3, var5, var6, var3, var4, var1[11] + -1120210379, 10);
        var4 = this.a(3, var4, var5, var6, var3, var1[2] + 718787259, 15);
        var3 = this.a(3, var3, var4, var5, var6, var1[9] + -343485551, 21);
        int[] var10000 = this.b;
        var10000[0] += var6;
        var10000 = this.b;
        var10000[1] += var3;
        var10000 = this.b;
        var10000[2] += var4;
        var10000 = this.b;
        var10000[3] += var5;
    }

    private void c(byte[] var1, int var2, int var3) {
        int var4 = var3;
        int var5 = (int)this.longa;
        this.longa += (long)(var3 << 3);
        if ((var5 = var5 >>> 3 & 63) != 0) {
            int var6 = var5;
            var5 = 64 - var5;
            if (var3 < var5) {
                System.arraycopy(var1, var2, this.bytes, var6, var3);
                return;
            }

            System.arraycopy(var1, var2, this.bytes, var6, var5);
            this.b();
            var2 += var5;
            var4 = var3 - var5;
        }

        while(var4 >= 64) {
            System.arraycopy(var1, var2, this.bytes, 0, 64);
            this.b();
            var2 += 64;
            var4 -= 64;
        }

        System.arraycopy(var1, var2, this.bytes, 0, var4);
    }

    public SecureUtill2() {
        this.b[0] = 1732584193;
        this.b[1] = -271733879;
        this.b[2] = -1732584194;
        this.b[3] = 271733878;
        this.longa = 0L;
        this.bytes = new byte[64];
        this.intarr = new int[16];
    }
}
