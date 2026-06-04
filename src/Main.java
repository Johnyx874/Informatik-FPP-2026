import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {

    public static void main(String[] args) {

        Window window = new Window(640, 480);

        Shader shader = new Shader();

        Texture tex = new Texture("./assets/test_texture.png");

        Object2D testObject = new Object2D(-0.5f,-0.5f, 1,1, tex);

        while (!glfwWindowShouldClose(window.window)) {
            glfwPollEvents();

            glClear(GL_COLOR_BUFFER_BIT);

//            tex.bind();
//
//            glBegin(GL_QUADS);
//                glTexCoord2f(0, 0);
//                glVertex2f(-0.5f, 0.5f);
//
//                glTexCoord2f(1, 0);
//                glVertex2f(0.5f, 0.5f);
//
//                glTexCoord2f(1, 1);
//                glVertex2f(0.5f, -0.5f);
//
//                glTexCoord2f(0, 1);
//                glVertex2f(-0.5f, -0.5f);
//            glEnd();

            testObject.render();

            glfwSwapBuffers(window.window);
        }

        shader.terminate();

        glfwTerminate();
    }
}
