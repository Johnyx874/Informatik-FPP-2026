import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {

    public static void main(String[] args) {

        Window window = new Window(640, 480);

        Shader shader = new Shader();

        Camera2D camera = new Camera2D(0, 0);

        //Texture tex = new Texture("./assets/test_texture.png");

        Texture texBackground = new Texture("./assets/background.png");

        Texture texPurpur = new Texture("./assets/purpur.png");

        Object2D Purpur = new Object2D(50,50, 100,100, texPurpur);

        Object2D Background = new Object2D(0,0, 1000,1000, texBackground);

        while (!glfwWindowShouldClose(window.window)) {
            glfwPollEvents();

            glClear(GL_COLOR_BUFFER_BIT);

            if (glfwGetKey(window.window, GLFW_KEY_LEFT) == GLFW_PRESS) {
                Purpur.position.x -= 1;
            }

            if (glfwGetKey(window.window, GLFW_KEY_RIGHT) == GLFW_PRESS) {
                Purpur.position.x += 1;
            }

            if (glfwGetKey(window.window, GLFW_KEY_UP) == GLFW_PRESS) {
                Purpur.position.y -= 1;
            }

            if (glfwGetKey(window.window, GLFW_KEY_DOWN) == GLFW_PRESS) {
                Purpur.position.y += 1;
            }


            if (glfwGetKey(window.window, GLFW_KEY_A) == GLFW_PRESS) {
                camera.position.x -= 0.1f;
            }

            if (glfwGetKey(window.window, GLFW_KEY_D) == GLFW_PRESS) {
                camera.position.x += 0.1f;
            }

            if (glfwGetKey(window.window, GLFW_KEY_W) == GLFW_PRESS) {
                camera.position.y -= 0.1f;
            }

            if (glfwGetKey(window.window, GLFW_KEY_S) == GLFW_PRESS) {
                camera.position.y += 0.1f;
            }


            Purpur.render(shader, camera);

            Background.render(shader, camera);

            glfwSwapBuffers(window.window);
        }

        shader.terminate();

        glfwTerminate();
    }
}