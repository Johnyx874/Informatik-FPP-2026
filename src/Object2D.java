import static org.lwjgl.opengl.GL30.*;

public class Object2D {

    public Vector2 position;

    public float width;
    public float height;

    public String name;

    public Texture texture;

    public int vao;
    public int vbo;

    public Object2D(String name, float x, float y, String texPath) {

        this.name = name;

        this.position = new Vector2(x, y);

        this.texture = new Texture(texPath);

        this.width = texture.width;
        this.height = texture.height;

        createMesh();

        System.out.println("Object2D " + this.name + " created!");
    }

    public Object2D(String name, float x, float y, float scale, String texPath) {

        this.name = name;

        this.position = new Vector2(x, y);

        this.texture = new Texture(texPath);

        this.width = texture.width * scale;
        this.height = texture.height * scale;

        createMesh();

        System.out.println("Object2D " + this.name + " created!");
    }

    public Object2D(String name, float x, float y, float width, float height, String texPath) {

        this.name = name;

        this.position = new Vector2(x, y);

        this.width = width;
        this.height = height;

        this.texture = new Texture(texPath);

        createMesh();

        System.out.println("Object2D " + this.name + " created!");
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

    boolean clicked = false;
    public void checkClicked(Mouse mouse) {

        if (!mouse.leftPressed && !mouse.rightPressed) {clicked = false;}

        boolean hovering =
            mouse.position.x > position.x &&
            mouse.position.x < position.x + width &&
            mouse.position.y > position.y &&
            mouse.position.y < position.y + height;

        if (hovering) {
            if (mouse.leftPressed && !clicked) {
                position.y += 10f;
                clicked = true;
                System.out.println("Object2D was left-clicked!");
            }
            if (mouse.rightPressed && !clicked) {
                position.y -= 10f;
                clicked = true;
                System.out.println("    Object2D was right-clicked!");
            }
        }
    }

    public void update() {}

}