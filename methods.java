import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.io.IOException;

class float2 {
    public float x, y;

    public float2(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

class float3 {
    public float x, y, z;

    public float3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

class screen {
    public int x_res, y_res;
    public float3 color;

    public screen(int x_res, int y_res , float3 color) {
        this.x_res = x_res;
        this.y_res = y_res;
        this.color = color;
    }
}

class triangle {
    public float3 a, b, c;

    public triangle(float3 a, float3 b, float3 c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}

public class methods {
    public static float3[][] generateTestImage(screen scrn) {
        float3[][] image = new float3[scrn.x_res][scrn.y_res];
        for (int y = 0; y < scrn.y_res; y++) {
            for (int x = 0; x < scrn.x_res; x++) {
                float r = x / (float)(scrn.x_res - 1);
                float g = y / (float)(scrn.y_res - 1);
                image[x][y] = new float3(r, g, 0f);
            }
        }
        return image;
    }

    public static void generateParseMetadata(String input_filename) throws IOException {
        OBJParser parser = new OBJParser(input_filename);
        
        List<float3> vertices = parser.parse("v");
        List<float3> normals = parser.parse("vn");
        List<float3> textures = parser.parse("vt");
        List<float3> faces = parser.parse("f");

        BufferedWriter out = new BufferedWriter(new FileWriter(input_filename + ".json"));
        
        out.write("{\n");
        out.write("  \"vertices\": [\n");
        for (int i = 0; i < vertices.size(); i++) {
            float3 v = vertices.get(i);
            out.write("    {\"x\": " + v.x + ", \"y\": " + v.y + ", \"z\": " + v.z + "}");
            if (i < vertices.size() - 1) {
                out.write(",");
            }
            out.write("\n");
        }
        out.write("  ],\n");

        out.write("  \"normals\": [\n");
        for (int i = 0; i < normals.size(); i++) {
            float3 n = normals.get(i);
            out.write("    {\"x\": " + n.x + ", \"y\": " + n.y + ", \"z\": " + n.z + "}");
            if (i < normals.size() - 1) {
                out.write(",");
            }
            out.write("\n");
        }
        out.write("  ],\n");

        out.write("  \"textures\": [\n");
        for (int i = 0; i < textures.size(); i++) {
            float3 t = textures.get(i);
            out.write("    {\"x\": " + t.x + ", \"y\": " + t.y + ", \"z\": " + t.z + "}");
            if (i < textures.size() - 1) {
                out.write(",");
            }
            out.write("\n");
        }
        out.write("  ],\n");

        out.write("  \"faces\": [\n");
        for (int i = 0; i < faces.size(); i++) {
            float3 f = faces.get(i);
            out.write("    {\"x\": " + f.x + ", \"y\": " + f.y + ", \"z\": " + f.z + "}");
            if (i < faces.size() - 1) {
                out.write(",");
            }
            out.write("\n");
        }
        out.write("  ]\n");

        out.write("}\n");
        out.close();
    }
}