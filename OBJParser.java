import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class OBJParser {
    public String input_filename;

    public OBJParser(String input_filename) {
        this.input_filename = input_filename;
    }

    public void parse() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(input_filename + ".obj"));
        String line;

        List<String> vertices = new ArrayList<>();
        List<String> normals = new ArrayList<>();
        List<String> textures = new ArrayList<>();
        List<String> faces = new ArrayList<>();

        while ((line = in.readLine()) != null) {
            String delim = line.split(" ")[0];
            String data = line.substring(delim.length()).trim();
            switch (delim) {
                case "v":
                    vertices.add(data);
                    break;
                case "vn":
                    normals.add(data);
                    break;
                case "vt":
                    textures.add(data);
                    break;
                case "f":
                    faces.add(data);
                    break;
                default:
                    in.readLine();
            }
            //System.out.println(line);
        }
        in.close();

        BufferedWriter out = new BufferedWriter(new FileWriter(input_filename + ".json"));
        out.write("{\n");

        out.write("  \"vertices\": [\n");
        for (int i = 0; i < vertices.size(); i++) {
            out.write("    " + vertices.get(i));
            if (i < vertices.size() - 1) {
                out.write(",");
            }
            out.write("\n");
        }
        out.write("  ],\n");

        out.write("  \"normals\": [\n");
        for (int i = 0; i < normals.size(); i++) {
            out.write("    " + normals.get(i));
            if (i < normals.size() - 1) {
                out.write(",");
            }
            out.write("\n");
        }
        out.write("  ],\n");

        out.write("  \"textures\": [\n");
        for (int i = 0; i < textures.size(); i++) {
            out.write("    " + textures.get(i));
            if (i < textures.size() - 1) {
                out.write(",");
            }
            out.write("\n");
        }
        out.write("  ],\n");

        out.write("  \"faces\": [\n");
        for (int i = 0; i < faces.size(); i++) {
            out.write("    " + faces.get(i));
            if (i < faces.size() - 1) {
                out.write(",");
            }
            out.write("\n");
        }
        out.write("  ]\n");

        out.write("}\n");
        out.close();
    }

    public static void main(String[] args) throws IOException {
        OBJParser parser = new OBJParser("Frequency Divider Enclosure");
        parser.parse();
    }
}