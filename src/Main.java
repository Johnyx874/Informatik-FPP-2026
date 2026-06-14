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
        Mouse mouse = new Mouse();

        Collision collision = new Collision();

        ArrayList<Object2D> allObject2D = new ArrayList<>();
        ArrayList<Object2D> allCollisionObject = new ArrayList<>();

        Object2D Purpur = new Object2D("Purpur", 50,50, 100,100, "./assets/purpur.png");
        Object2D Background = new Object2D("BG", 0,0, "./assets/background.png");
        Object2D Test = new Object2D("Test", 500,500,150,150, "./assets/test_texture.png");
        Object2D Test2 = new Object2D("test2", -150,-150, 50,50, "./assets/test_texture.png");

        //Station station = new Station(1000,1000);

        ObjectGUI Button = new ObjectGUI("Button", 50, 300, 100, 100, "./assets/button.gif" );

        allObject2D.add(Background);
        allObject2D.add(Purpur);
        allObject2D.add(Test);
        allObject2D.add(Test2);

        allCollisionObject.add(Purpur);
        allCollisionObject.add(Test);
        allCollisionObject.add(Test2);

        float playerSpeed = 2f;
        float camSpeed = 2.5f;

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

            //camera.position.x = Purpur.position.x - (window.width / 2);
            //camera.position.y = Purpur.position.y - (window.height / 2);

            mouse.update(window.window, camera);

            Purpur.checkClicked(mouse);

            //station.update(window);

            Button.checkClicked(mouse);

            collision.check(allCollisionObject);

            renderer.renderObject(allObject2D);

            //renderer.renderObject(station.allStationParts);

            renderer.renderGUI(Button);

            glfwSwapBuffers(window.window);
        }

        shader.terminate();

        glfwTerminate();
    }
}