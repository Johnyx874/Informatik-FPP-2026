import org.lwjgl.glfw.GLFWVidMode;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;

public class Main {
    public static void main(String[] args) {
        if (!glfwInit()) {
            throw new IllegalStateException("Failed to initialize GLFW!");
        }

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        long window = glfwCreateWindow(640, 480, "LWJGL Test Program", 0, 0);
        if (window == 0) {
            throw new IllegalStateException("Failed to create window!");
        }

        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (videoMode.width() - 640) / 2, (videoMode.height() - 480) / 2);

        glfwShowWindow(window);

        glfwMakeContextCurrent(window);

        GL.createCapabilities();

        glEnable(GL_TEXTURE_2D);

        Texture tex = new Texture("./assets/test_texture.png");

        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents();

            glClear(GL_COLOR_BUFFER_BIT);

            tex.bind();

            glBegin(GL_QUADS);
                glTexCoord2f(0, 0);
                glVertex2f(-0.5f, 0.5f);

                glTexCoord2f(1, 0);
                glVertex2f(0.5f, 0.5f);

                glTexCoord2f(1, 1);
                glVertex2f(0.5f, -0.5f);

                glTexCoord2f(0, 1);
                glVertex2f(-0.5f, -0.5f);
            glEnd();


            glfwSwapBuffers(window);
        }

        glfwTerminate();
    }
}
