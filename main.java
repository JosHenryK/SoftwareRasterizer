import java.io.IOException;

class main {
    public static void main(String[] args) throws IOException {
        screen scrn = new screen(1920, 1080 , new float3(1.0f, 0.0f, 0.0f));
        triangle tri = new triangle(new float3(0.5f * scrn.x_res , 0.2f * scrn.y_res , 0), new float3(0.3f * scrn.x_res , 0.9f * scrn.y_res , 0), new float3(0.7f * scrn.x_res , 0.4f * scrn.y_res , 0));
        float3[][] image = TriangleRenderer.renderTriangle(scrn, tri , new float3(0.0f, 0.0f, 1.0f));
        BMPWriter.writeImageToBMP(image , "output_image");
    }
}