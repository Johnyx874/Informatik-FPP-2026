import javax.swing.*;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {

    public static void main(String[] args) {

        Window window = new Window(1280, 720);
        Shader shader = new Shader();
        Camera2D camera = new Camera2D(0, 0);
        Renderer2D renderer = new Renderer2D(window, shader, camera);

        ArrayList<Object2D> allObject2D = new ArrayList<>();

        Object2D Purpur = new Object2D(50,50, 100,100, "./assets/purpur.png");
        Object2D Background = new Object2D(0,0, "./assets/background.png");

        allObject2D.add(Background);
        allObject2D.add(Purpur);

        float playerSpeed = 1f;
        float camSpeed = 10f;

        while (!glfwWindowShouldClose(window.window)) {
            glfwPollEvents();

            glClear(GL_COLOR_BUFFER_BIT);

            if (glfwGetKey(window.window, GLFW_KEY_LEFT) == GLFW_PRESS) {
                Purpur.position.x -= playerSpeed;
            }

            if (glfwGetKey(window.window, GLFW_KEY_RIGHT) == GLFW_PRESS) {
                Purpur.position.x += playerSpeed;
            }

            if (glfwGetKey(window.window, GLFW_KEY_UP) == GLFW_PRESS) {
                Purpur.position.y -= playerSpeed;
            }

            if (glfwGetKey(window.window, GLFW_KEY_DOWN) == GLFW_PRESS) {
                Purpur.position.y += playerSpeed;
            }


            if (glfwGetKey(window.window, GLFW_KEY_A) == GLFW_PRESS) {
                camera.position.x -= camSpeed;
            }

            if (glfwGetKey(window.window, GLFW_KEY_D) == GLFW_PRESS) {
                camera.position.x += camSpeed;
            }

            if (glfwGetKey(window.window, GLFW_KEY_W) == GLFW_PRESS) {
                camera.position.y -= camSpeed;
            }

            if (glfwGetKey(window.window, GLFW_KEY_S) == GLFW_PRESS) {
                camera.position.y += camSpeed;
            }

            renderer.renderAllObjects(allObject2D);

            glfwSwapBuffers(window.window);
        }

        shader.terminate();

        glfwTerminate();
    }
}