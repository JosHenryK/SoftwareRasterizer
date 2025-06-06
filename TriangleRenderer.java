import java.io.IOException;

public class TriangleRenderer {
    public static float dot(float2 a, float2 b) {
        return (a.x * b.x) + (a.y * b.y);
    }

    public static float2 perp(float2 a) {
        return new float2(a.y , -a.x);
    }

    public static boolean pointOnRightSideOfLine(float2 a, float2 b, float2 p) {
        float2 ap = new float2(p.x - a.x , p.y - a.y);
        float2 ab_perp = perp(new float2(b.x - a.x , b.y - a.y));
        return dot(ab_perp , ap) >= 0;
    }

    public static boolean pointInTriangle(float2 a, float2 b, float2 c, float2 p) {
        return (pointOnRightSideOfLine(a, b, p) && pointOnRightSideOfLine(b, c, p) && pointOnRightSideOfLine(c, a, p));
    }

    public static float2 projectToScreen(float3 a) {
        return new float2(a.x, a.y);
    }

    public static float3[][] renderTriangle(screen scrn, triangle tri , float3 color) {
        float3 image[][] = new float3[scrn.x_res][scrn.y_res];
        for (int x = 0 ; x < image.length ; x++) {
            for (int y = 0 ; y < image[0].length ; y++) {
                float z = 0;
                float3 p = new float3(x, y , z);
                boolean inside = TriangleRenderer.pointInTriangle(projectToScreen(tri.a) , projectToScreen(tri.b) , projectToScreen(tri.c), projectToScreen(p));
                if (inside) {
                    image[x][y] = color;
                } else {
                    image[x][y] = scrn.color;
                }
            }
        }
        return image;
    }

    public static void main(String[] args) throws IOException{

    }
}