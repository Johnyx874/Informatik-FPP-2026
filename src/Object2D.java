import static org.lwjgl.opengl.GL30.*;

public class Object2D {

    protected float x;
    protected float y;

    protected float width;
    protected float height;

    protected Texture texture;

    protected int vao;
    protected int vbo;

    public Object2D(float x, float y,
                    float width, float height,
                    Texture texture) {

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.texture = texture;

        createMesh();
    }

    protected void createMesh() {

        float[] vertices = {

                // Position      // UV

                x,         y,          0.0f, 0.0f,
                x+width,   y,          1.0f, 0.0f,
                x+width,   y+height,   1.0f, 1.0f,

                x,         y+height,   0.0f, 1.0f
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
}