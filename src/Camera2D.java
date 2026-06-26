import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;

public class Camera2D {

    public Vector2 position;

    public float width;
    public float height;

    public float moveSpeed;

    public float zoom;

    public Camera2D(float x, float y, float width, float height) {
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.moveSpeed = 500f;
        this.zoom = 1;
        Log.print("Camera2D created!");
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }

    /*
    public Vector2 worldToCamera(Vector2 worldPos) {

        float camX = worldPos.x - position.x + width / 2f;
        float camY = worldPos.y - position.y + height / 2f;

        return new Vector2(camX, camY);
    }
    */


    public void update(Input input, float deltaTime) {
        if (input.pressed(GLFW_KEY_LEFT)) {
            position.x -= moveSpeed * deltaTime;
        }
        if (input.pressed(GLFW_KEY_RIGHT)) {
            position.x += moveSpeed * deltaTime;
        }
        if (input.pressed(GLFW_KEY_UP)) {
            position.y -= moveSpeed * deltaTime;
        }
        if (input.pressed(GLFW_KEY_DOWN)) {
            position.y += moveSpeed * deltaTime;
        }

        if (input.clicked(GLFW_KEY_BACKSPACE)) {
            zoom -= zoom * 0.1f;
            if (zoom < 0.1f) { zoom = 0.1f; };
        }
        if (input.clicked(GLFW_KEY_ENTER)) {
            zoom += zoom * 0.1f;
            if (zoom > 10) { zoom = 10f; };
        }
        if (input.clicked(GLFW_KEY_Y) || input.clicked(GLFW_KEY_Z)) {
            zoom = 1;
        }
    }
}