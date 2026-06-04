import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.*;

public class Window {

    int width;
    int height;
    long window;

    public Window(int w, int h) {

        width = w;
        height = h;

        if (!glfwInit()) {
            throw new IllegalStateException("Failed to initialize GLFW!");
        }

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        window = glfwCreateWindow(width, height, "LWJGL Test Program", 0, 0);
        if (window == 0) {
            throw new IllegalStateException("Failed to create window!");
        }

        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);

        glfwShowWindow(window);

        glfwMakeContextCurrent(window);

        GL.createCapabilities();

        glEnable(GL_TEXTURE_2D);

    }



}
