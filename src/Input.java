import static org.lwjgl.glfw.GLFW.*;

public class Input {

    public Mouse mouse = new Mouse();

    private final boolean[] keys = new boolean[GLFW_KEY_LAST + 1];
    private final boolean[] lastKeys = new boolean[GLFW_KEY_LAST + 1];

    public void update(Window window, Camera2D camera) {

        System.arraycopy(keys, 0, lastKeys, 0, keys.length);

        for (int key = 32; key <= GLFW_KEY_LAST; key++) {
            keys[key] =
                    glfwGetKey(window.window, key) == GLFW_PRESS;
        }

        mouse.update(window.window, camera);
    }

    public boolean pressed(int key) {
        return keys[key];
    }

    public boolean clicked(int key) {
        return keys[key] && !lastKeys[key];
    }

    public boolean released(int key) {
        return !keys[key] && lastKeys[key];
    }
}
