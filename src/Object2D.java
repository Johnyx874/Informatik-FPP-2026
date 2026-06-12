import static org.lwjgl.opengl.GL30.*;

public class Object2D {

    public Vector2 position;

    private float width;
    private float height;

    public Texture texture;

    public int vao;
    public int vbo;

    public Object2D(float x, float y, String texPath) {

        this.position = new Vector2(x, y);

        this.texture = new Texture(texPath);

        this.width = texture.width;
        this.height = texture.height;

        createMesh();
    }

    public Object2D(float x, float y, float width, float height, String texPath) {

        this.position = new Vector2(x, y);

        this.texture = new Texture(texPath);

        this.width = width;
        this.height = height;

        createMesh();
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

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}