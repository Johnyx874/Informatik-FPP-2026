import static org.lwjgl.glfw.GLFW.*;

public class Mouse {

    public Vector2 screenPos;
    public Vector2 position;

    public boolean leftPressed;
    public boolean rightPressed;

    public Mouse() {
        this.screenPos = new Vector2();
        this.position = new Vector2();
        this.leftPressed = false;
        this.rightPressed = false;
    }

    public void update(long window, Camera2D camera) {

        double[] mouseX = new double[1];
        double[] mouseY = new double[1];

        glfwGetCursorPos(window, mouseX, mouseY);

        screenPos.x = (float) mouseX[0];
        screenPos.y = (float) mouseY[0];

        position.x = screenPos.x + camera.position.x;
        position.y = screenPos.y + camera.position.y;

        leftPressed =
                glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_LEFT)
                        == GLFW_PRESS;

        rightPressed =
                glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_RIGHT)
                        == GLFW_PRESS;
    }
}