import static org.lwjgl.opengl.GL30.*;

public class Object2D {

    private Vector2 position;
    private float x;
    private float y;

    private float width;
    private float height;

    private Texture texture;

    private int vao;
    private int vbo;

    public Object2D(float x, float y,
                    float width, float height,
                    Texture texture) {

        this.position = new Vector2(x, y);

        this.width = width;
        this.height = height;

        this.texture = texture;

        createMesh();
    }

    public Vector2 getPosition() {
        return position;
    }

    private void createMesh() {

        float[] vertices = {

                // Position            // UV

                0.0f,    0.0f,          0.0f, 0.0f,
                width,   0.0f,          1.0f, 0.0f,
                width,   height,        1.0f, 1.0f,

                0.0f,    height,        0.0f, 1.0f
        };

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);

        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        // Position
        glVertexAttribPointer(
                0,
                2,
                GL_FLOAT,
                false,
                4 * Float.BYTES,
                0
        );

        glEnableVertexAttribArray(0);

        // UV
        glVertexAttribPointer(
                1,
                2,
                GL_FLOAT,
                false,
                4 * Float.BYTES,
                2 * Float.BYTES
        );

        glEnableVertexAttribArray(1);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void render() {

        texture.bind();

        glBindVertexArray(vao);

        glDrawArrays(GL_TRIANGLE_FAN, 0, 4);

        glBindVertexArray(0);
    }

    public void render(Camera2D camera, Shader shader) {

        // Position relative to camera
        Vector2 camPos = camera.worldToCamera(position);

        // Parameters to send to shader
        float renderX = camPos.x;
        float renderY = camPos.y;



        shader.bind();

        glBindVertexArray(vao);

        shader.setVec2("u_Position", renderX, renderY);

        glDrawArrays(GL_TRIANGLE_FAN, 0, 4);

        shader.unbind();

        glBindVertexArray(0);

        System.out.println("Rendering at: " + renderX + ", " + renderY);
    }
}