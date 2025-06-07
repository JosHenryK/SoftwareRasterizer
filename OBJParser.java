import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

class OBJParser {
    public String input_filename;

    public OBJParser(String input_filename) {
        this.input_filename = input_filename;
    }

    public List<float3> parse(String returnDelim) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(input_filename + ".obj"));

        String line;
        List<float3> vertices = new ArrayList<>();
        List<float3> normals = new ArrayList<>();
        List<float3> textures = new ArrayList<>();
        List<float3> faces = new ArrayList<>();

        while ((line = in.readLine()) != null) {
            String fileDelim = line.split(" ")[0];
            String[] data = (line.substring(fileDelim.length()).trim()).split(" ");
            switch(fileDelim) {
                case "v":
                    float3 v_coord = new float3(Float.parseFloat(data[0]) , Float.parseFloat(data[1]) , Float.parseFloat(data[2]));
                    vertices.add(v_coord);
                    break;
                case "vn":
                    float3 vn_coord = new float3(Float.parseFloat(data[0]) , Float.parseFloat(data[1]) , Float.parseFloat(data[2]));
                    normals.add(vn_coord);
                    break;
                case "vt":
                    float3 vt_coord = new float3(Float.parseFloat(data[0]) , Float.parseFloat(data[1]) , Float.parseFloat(data[2]));
                    textures.add(vt_coord);
                    break;
                case "f":
                    float3 f_vtx_list = new float3(Float.valueOf(data[0].split("//")[0]) , Float.valueOf(data[1].split("//")[0]) , Float.valueOf(data[2].split("//")[0]));
                    faces.add(f_vtx_list);
                    break;
                default:
                    in.readLine();
            }
        }
        in.close();

        switch(returnDelim) {
            case "v":
                return vertices;
            case "vn":
                return normals;
            case "vt":
                return textures;
            case "f":
                return faces;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) throws IOException {

    }
}