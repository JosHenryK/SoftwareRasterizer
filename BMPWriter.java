import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BMPWriter {
    public static void writeImageToBMP(float3[][] image, String filename) throws IOException {
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
                    float3 col = image[x][y];
                    out.writeByte((int)(col.x * 255)); // Blue
                    out.writeByte((int)(col.y * 255)); // Green
                    out.writeByte((int)(col.z * 255)); // Red
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
        
    }
}