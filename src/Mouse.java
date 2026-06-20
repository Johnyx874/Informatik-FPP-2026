import static org.lwjgl.glfw.GLFW.*;

public class Mouse {

    public Vector2 screenPos;
    public Vector2 position;

    public boolean leftPressed, lastLeftPressed;
    public boolean rightPressed, lastRightPressed;

    public Mouse() {
        this.screenPos = new Vector2();
        this.position = new Vector2();
        this.leftPressed = false;
        this.rightPressed = false;
    }

    public boolean leftClicked() {
        return leftPressed && !lastLeftPressed;
    }

    public boolean rightClicked() {
        return rightPressed && !lastRightPressed;
    }

    public boolean leftHeld() {
        return leftPressed;
    }

    public boolean rightHeld() {
        return rightPressed;
    }

    public boolean hovering(Object2D object) {
        return this.position.x > object.position.x &&
               this.position.x < object.position.x + object.width &&
               this.position.y > object.position.y &&
               this.position.y < object.position.y + object.height;
    }

    public boolean leftClicked(Object2D object) {
        return hovering(object) && this.leftClicked();
    }

    public boolean rightClicked(Object2D object) {
        return hovering(object) && this.rightClicked();
    }

    public boolean leftHeld(Object2D object) {
        return hovering(object) && this.leftPressed;
    }

    public boolean rightHeld(Object2D object) {
        return hovering(object) && this.rightPressed;
    }


    public void update(long window, Camera2D camera) {

        lastLeftPressed = leftPressed;
        lastRightPressed = rightPressed;

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