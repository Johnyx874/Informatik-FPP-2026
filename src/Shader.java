import static org.lwjgl.opengl.GL30.*;

public class Shader {

    public int shader2D;
    public int shaderGUI;

    String vertexShader2D;
    String vertexShaderGUI;
    String fragmentShader;
    String fragmentShaderLighting;

    public Shader(Window window) {

        vertexShader2D =
                "#version 330 core\n" +
                "\n" +
                "layout(location = 0) in vec2 aPos;\n" +
                "layout(location = 1) in vec2 aUV;\n" +
                "\n" +
                "uniform vec2 u_Position;\n" +
                "uniform vec2 u_ScreenSize;\n" +
                "uniform vec2 u_CameraPosition;\n" +
                "uniform float u_CameraZoom;\n" +
                "\n" +
                "out vec2 vUV;\n" +
                "\n" +
                "void main()\n" +
                "{\n" +
                "    // calculate world position\n" +
                "    vec2 worldPosition = aPos + u_Position;\n" +
                "    // apply camera position\n" +
                "    worldPosition -= u_CameraPosition;\n" +
                "    // apply camera zoom;\n" +
                "    worldPosition *= u_CameraZoom;\n" +
                "    // center camera\n" +
                "    worldPosition += u_ScreenSize * 0.5;\n" +
                "\n" +
                "    // Pixel -> NDC umrechnen\n" +
                "    vec2 ndc;\n" +
                "\n" +
                "    ndc.x = (worldPosition.x / u_ScreenSize.x) * 2.0 - 1.0;\n" +
                "\n" +
                "    ndc.y = 1.0 - (worldPosition.y / u_ScreenSize.y) * 2.0;\n" +
                "\n" +
                "    // Finale OpenGL Position\n" +
                "    gl_Position = vec4(ndc, 0.0, 1.0);\n" +
                "\n" +
                "    // UV weitergeben\n" +
                "    vUV = aUV;\n" +
                "}\n";

        vertexShaderGUI =
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
                        "    FragColor = texture(u_Texture, vUV);" +
                        "}\n";

        fragmentShaderLighting =
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
                "    float dx = abs(1280/2 - gl_FragCoord.x);" +
                "    float dy = abs(720/2 - gl_FragCoord.y);" +
                "    float distance = sqrt(dx * dx + dy * dy);" +
                "    float normalizedAlpha = 1 - (distance / 400);" +
                "    FragColor = texture(u_Texture, vUV);" +
                "    if (FragColor.a == 1) {FragColor.a = normalizedAlpha;}" +
                "}\n";

        shader2D = createShader(vertexShader2D, fragmentShader);

        bind2D();
        setVec2_2D("u_ScreenSize", window.width, window.height);
        unbind();

        Log.print("Shader 2D created!");


        shaderGUI = createShader(vertexShaderGUI, fragmentShader);

        bindGUI();
        setVec2_GUI("u_ScreenSize", window.width, window.height);
        unbind();

        Log.print("Shader GUI created!");


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

    public void bind2D() {
        glUseProgram(shader2D);
    }
    public void bindGUI() {
        glUseProgram(shaderGUI);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void setVec2_2D(String name, float x, float y) {
        int location = glGetUniformLocation(shader2D, name);
        glUniform2f(location, x, y);
    }

    public void setVec2_GUI(String name, float x, float y) {
        int location = glGetUniformLocation(shaderGUI, name);
        glUniform2f(location, x, y);
    }

    public void setCameraPosition(float x, float y) {
        int location = glGetUniformLocation(shader2D, "u_CameraPosition");
        glUniform2f(location, x, y);
    }

    public void setCameraZoom(float zoom) {
        int location = glGetUniformLocation(shader2D, "u_CameraZoom");
        glUniform1f(location, zoom);
    }

    public void terminate() {
        glDeleteProgram(shader2D);
        glDeleteProgram(shaderGUI);
    }
}