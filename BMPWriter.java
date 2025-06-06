import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class Float3 {
    public float x, y, z;

    public Float3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getR() {
        return x;
    }
    
    public float getG() {
        return y;
    }

    public float getB() {
        return z;
    }

    public void setR(float r) {
        x = r;
    }

    public void setG(float g) {
        y = g;
    }

    public void setB(float b) {
        z = b;
    }
}

public class BMPWriter {
    public static Float3[][] createTestImage(int width, int height) {
        Float3[][] image = new Float3[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                float r = x / (float)(width - 1);
                float g = y / (float)(height - 1);
                image[x][y] = new Float3(r, g, 0f);
            }
        }
        return image;
    }

    public static void writeImageToBMP(Float3[][] image, String filename) throws IOException {
        int width = image.length;
        int height = image[0].length;
        int rowSize = ((24 * width + 31) / 32) * 4;
        int pixelDataSize = rowSize * height;
        int fileSize = 14 + 40 + pixelDataSize;

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(filename + ".bmp"))) {
            // --- BMP Header ---
            out.writeBytes("BM");
            out.writeInt(Integer.reverseBytes(fileSize)); // File size
            out.writeInt(0);                              // Reserved
            out.writeInt(Integer.reverseBytes(54));       // Pixel data offset

            // --- DIB Header ---
            out.writeInt(Integer.reverseBytes(40));       // DIB header size
            out.writeInt(Integer.reverseBytes(width));
            out.writeInt(Integer.reverseBytes(height));
            out.writeShort(Short.reverseBytes((short)1)); // Color planes
            out.writeShort(Short.reverseBytes((short)24));// Bits per pixel
            out.writeInt(0);                              // Compression
            out.writeInt(Integer.reverseBytes(pixelDataSize)); // Image size
            out.writeInt(Integer.reverseBytes(2835));     // X Pixels per meter
            out.writeInt(Integer.reverseBytes(2835));     // Y Pixels per meter
            out.writeInt(0);                              // Colors used
            out.writeInt(0);                              // Important colors

            // --- Pixel Data (bottom-up BGR format) ---
            for (int y = height - 1; y >= 0; y--) {
                for (int x = 0; x < width; x++) {
                    Float3 col = image[x][y];
                    out.writeByte((int)(col.getB() * 255)); // Blue
                    out.writeByte((int)(col.getG() * 255)); // Green
                    out.writeByte((int)(col.getR() * 255)); // Red
                }

                // Row padding
                int padding = rowSize - (width * 3);
                for (int i = 0; i < padding; i++) {
                    out.writeByte(0);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int width = 64;
        int height = 64;
        Float3[][] image = createTestImage(width, height);
        writeImageToBMP(image, "test_image");
        System.out.println("BMP image written to test_image.bmp");
    }
}