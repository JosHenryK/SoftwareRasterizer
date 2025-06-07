import java.util.List;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        screen scrn = new screen(1920, 1080 , new float3(1.0f, 0.0f, 0.0f));
        OBJParser parser = new OBJParser("Frequency Divider Enclosure");
        List<float3> vertices = parser.parse("v");
        List<float3> faces = parser.parse("f");
        triangle tri = new triangle(vertices.get((int)(faces.get(0).x - 1)) ,
                                    vertices.get((int)(faces.get(0).y - 1)) ,
                                    vertices.get((int)(faces.get(0).z - 1)));
        System.out.printf(
            "%f %f %f %f %f %f %f %f %f\n",
            tri.a.x, tri.a.y, tri.a.z,
            tri.b.x, tri.b.y, tri.b.z,
            tri.c.x, tri.c.y, tri.c.z
        );
        int s = 1000;
        tri.a.x = Math.abs(s * tri.a.x);
        tri.a.y = Math.abs(s * tri.a.y);
        tri.a.z = Math.abs(s * tri.a.z);
        tri.b.x = Math.abs(s * tri.b.x);
        tri.b.y = Math.abs(s * tri.b.y);
        tri.b.z = Math.abs(s * tri.b.z);
        tri.c.x = Math.abs(s * tri.c.x);
        tri.c.y = Math.abs(s * tri.c.y);
        tri.c.z = Math.abs(s * tri.c.z);
        System.out.printf(
            "%f %f %f %f %f %f %f %f %f\n",
            tri.a.x, tri.a.y, tri.a.z,
            tri.b.x, tri.b.y, tri.b.z,
            tri.c.x, tri.c.y, tri.c.z
        );
        float3[][] image = triangleRenderer.renderTriangle(scrn, tri , new float3(0.0f, 0.0f, 1.0f));
        BMPWriter.writeImageToBMP(image , "output_image");
    }
}