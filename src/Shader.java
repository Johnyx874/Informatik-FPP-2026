import static org.lwjgl.opengl.GL30.*;

public class Shader {

    public int shader;
    String vertexShader;
    String fragmentShader;

    public Shader() {

        vertexShader =
                "#version 330 core\n" +
                "\n" +
                "layout(location = 0) in vec4 position;\n" +
                "\n" +
                "void main()\n" +
                "{\n" +
                "   gl_Position = position;\n" +
                "}\n";

        fragmentShader =
                "#version 330 core\n" +
                "in vec2 v_TexCoord;\n" +
                "layout(location = 0) out vec4 color;\n" +
                "uniform sampler2D u_Texture;\n" +
                "void main()\n" +
                "{\n" +
                "   color = texture(u_Texture, v_TexCoord);\n" +
                "}\n";

        shader = createShader(vertexShader, fragmentShader);

        int location = glGetUniformLocation(shader, "u_Texture");

        glUniform1i(location, 0);

        glUseProgram(shader);
    }

    int compileShader(int type, String source) {

        int id = glCreateShader(type);
        glShaderSource(id, source);
        glCompileShader(id);

        int result = glGetShaderi(id, GL_COMPILE_STATUS);
        if (result == GL_FALSE) {
            String message = glGetShaderInfoLog(id);
            System.out.println("Failed to compile " + (type == GL_VERTEX_SHADER ? "vertex" : "fragment") + " shader!");
            System.out.println(message);
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
        glValidateProgram(program);

        glDeleteShader(vs);
        glDeleteShader(fs);

        return program;
    }

    public void terminate() {
        glDeleteProgram(shader);
    }
}
