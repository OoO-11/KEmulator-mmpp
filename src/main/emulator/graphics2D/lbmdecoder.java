package emulator.graphics2D;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class lbmdecoder {
    public static BufferedImage decodelbm(byte[] array) {
        System.out.println("lbm image");

        // ByteBuffer 생성 (Little Endian)
        ByteBuffer buffer = ByteBuffer.wrap(array).order(ByteOrder.LITTLE_ENDIAN);

        // 매직 넘버 읽기 (4바이트)
        byte[] magicBytes = new byte[4];
        buffer.get(magicBytes);
//        String magicNumber = new String(magicBytes, StandardCharsets.US_ASCII);
//        if (!"LBMP".equals(magicNumber)) {
//            throw new IllegalArgumentException("Invalid magic number: " + magicNumber);
//        }

        // Type 읽기 (4바이트)
        int type = buffer.getInt();
        System.out.println("Type: " + type);

        // Width 읽기 (4바이트)
        int width = buffer.getInt();
        System.out.println("Width: " + width);

        // Height 읽기 (4바이트)
        int height = buffer.getInt();
        System.out.println("Height: " + height);

        // Size 읽기 (4바이트)
        int size = buffer.getInt();
        System.out.println("Size: " + size);

        // Mask 읽기 (4바이트)
        int mask = buffer.getInt();
        System.out.println("Mask: " + mask);

        // Data 읽기 (size만큼)
        byte[] data = new byte[size];
        buffer.get(data);
        System.out.println("Data length: " + data.length);


        assert (width * height == size) : "now only support type 8";

        // 마스크 읽기 (옵션)
        byte[] maskPlane = null;
        if (mask == 1) {
            int maskPlaneSize = (height + 7) / 8;; // 비트 단위 마스크 크기
            maskPlane = new byte[maskPlaneSize*width];
            buffer.get(maskPlane);
        }

        // BufferedImage 생성
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int index = 0;

        // 픽셀 데이터를 ARGB 값으로 변환
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (index >= data.length) {
                    throw new IllegalArgumentException("Data size does not match image dimensions.");
                }

                // 픽셀의 R, G, B 값을 rrrgggbb 형식에서 추출
                int pixelByte = data[index++] & 0xFF;
                int r = (pixelByte >> 5) & 0b111;  // 상위 3비트
                int g = (pixelByte >> 2) & 0b111;  // 중간 3비트
                int b = pixelByte & 0b11;          // 하위 2비트

                // 각 색상을 0~255 범위로 확장 (3비트 -> 8비트, 2비트 -> 8비트)
                int red = (r << 5) | (r << 2) | (r >> 1);
                int green = (g << 5) | (g << 2) | (g >> 1);
                int blue = (b << 6) | (b << 4) | (b << 2) | b;

                // 알파 값을 마스크 플레인에서 가져오기
                int alpha = 255; // 기본값 (완전히 불투명)
                if (mask == 1) {
                    int maskPlaneIndex = (y >> 3) * width + x; // 마스크 데이터의 1D 인덱스
                    int bitindex = y & 7;
                    if(((maskPlane[maskPlaneIndex] >> bitindex) & 1) == 1){
                        alpha = 0;
                    }
                }

                // ARGB 값 설정
                int argb = (alpha << 24) | (red << 16) | (green << 8) | blue;
                image.setRGB(x, y, argb);
            }
        }

        return image;
    }
}
