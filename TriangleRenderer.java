import java.io.IOException;

class float2 {
    public float x, y;

    public float2(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

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

    public static void main(String[] args) throws IOException{
        int x_res = 1920, y_res = 1080;

        float2[] test_point = {
            new float2(0.5f * x_res, 0.2f * y_res),
            new float2(0.3f * x_res, 0.9f * y_res),
            new float2(0.7f * x_res, 0.4f * y_res)
        };

        float3 image[][] = new float3[x_res][y_res];

        for (int x = 0 ; x < x_res ; x++) {
            for (int y = 0 ; y < y_res ; y++) {
                float2 p = new float2(x, y);
                boolean inside = pointInTriangle(test_point[0] , test_point[1] , test_point[2], p);
                if (inside) {
                    image[x][y] = new float3(0.0f, 0.0f, 1.0f); // Red color for inside
                    //System.out.println("Point (" + x + ", " + y + ") is inside the triangle.");
                } else {
                    image[x][y] = new float3(1.0f, 0.0f, 0.0f); // Blue color for outside
                    //System.out.println("Point (" + x + ", " + y + ") is outside the triangle.");
                }
            }
        }
    BMPWriter.writeImageToBMP(image, "output_image");
    }
}