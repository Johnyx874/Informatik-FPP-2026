import static org.lwjgl.opengl.GL30.*;

public class Shader {

    public int shader;
    String vertexShader;
    String fragmentShader;

    public Shader() {

        vertexShader =
                "#version 330 core\n" +
                "\n" +
                "layout(location = 0) in vec2 aPos;\n" +
                "layout(location = 1) in vec2 aUV;\n" +
                "\n" +
                "uniform vec2 u_Position;\n" +
                "uniform vec2 u_ScreenSize;\n" +
                "\n" +
                "out vec2 vUV;\n" +
                "\n" +
                "void main()\n" +
                "{\n" +
                "    // Objektposition anwenden\n" +
                "    vec2 pixelPos = aPos + u_Position;\n" +
                "\n" +
                "    // Pixel -> NDC umrechnen\n" +
                "    vec2 ndc;\n" +
                "\n" +
                "    ndc.x = (pixelPos.x / u_ScreenSize.x) * 2.0 - 1.0;\n" +
                "\n" +
                "    ndc.y = 1.0 - (pixelPos.y / u_ScreenSize.y) * 2.0;\n" +
                "\n" +
                "    // Finale OpenGL Position\n" +
                "    gl_Position = vec4(ndc, 0.0, 1.0);\n" +
                "\n" +
                "    // UV weitergeben\n" +
                "    vUV = aUV;\n" +
                "}\n";

        fragmentShader =
                "#version 330 core\n" +
                "\n" +
                "in vec2 vUV;\n" +
                "\n" +
                "uniform sampler2D u_Texture;\n" +
                "\n" +
                "out vec4 FragColor;\n" +
                "\n" +
                "void main()\n" +
                "{\n" +
                "    FragColor = texture(u_Texture, vUV);\n" +
                "}\n";

        shader = createShader(vertexShader, fragmentShader);

        glUseProgram(shader);

        int location = glGetUniformLocation(shader, "u_Texture");

        glUniform1i(location, 0);

        Log.print("Shader created!");
    }

    int compileShader(int type, String source) {

        int id = glCreateShader(type);
        glShaderSource(id, source);
        glCompileShader(id);

        int result = glGetShaderi(id, GL_COMPILE_STATUS);
        if (result == GL_FALSE) {
            String message = glGetShaderInfoLog(id);
            Log.print("Failed to compile " + (type == GL_VERTEX_SHADER ? "vertex" : "fragment") + " shader!");
            Log.print(message);
            glDeleteShader(id);
            return 0;
        }

        return id;
    }

    int createShader(String vertexShader, String fragmentShader) {

        int program = glCreateProgram();
        int vs = compileShader(GL_VERTEX_SHADER, vertexShader);
        int fs = compileShader(GL_FRAGMENT_SHADER, fragmentShader);

        glAttachShader(program, vs);
        glAttachShader(program, fs);

        glLinkProgram(program);
        if (glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE) {
            Log.print(glGetProgramInfoLog(program));
        }

        glValidateProgram(program);
        if (glGetProgrami(program, GL_VALIDATE_STATUS) == GL_FALSE) {
            Log.print(glGetProgramInfoLog(program));
        }

        glDeleteShader(vs);
        glDeleteShader(fs);

        return program;
    }

    public void bind() {
        glUseProgram(shader);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void setVec2(String name, float x, float y) {

        int location = glGetUniformLocation(shader, name);
        glUniform2f(location, x, y);
    }

    public void terminate() {
        glDeleteProgram(shader);
    }
}