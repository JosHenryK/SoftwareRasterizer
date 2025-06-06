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
    
}