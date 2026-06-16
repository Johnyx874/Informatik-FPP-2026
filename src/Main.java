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
        ArrayList<PhysicsObject> allPhysicsObject = new ArrayList<>();

        Object2D Background = new Object2D
                ("BG",0,0, 0.5f, "./assets/background.png");
                allObject2D.add(Background);

        PhysicsObject Purpur = new PhysicsObject
                ("Purpur", 50,50, 100,100, "./assets/purpur.png");
                Purpur.setCollider(true);
                allObject2D.add(Purpur);
                allPhysicsObject.add(Purpur);

        PhysicsObject Test = new PhysicsObject
                ("Test", 0,-150,100,100, "./assets/box.png");
                Test.setCollider(true);
                allObject2D.add(Test);
                allPhysicsObject.add(Test);

        PhysicsObject Test2 = new PhysicsObject
                ("test2", -150,0, 150,150, "./assets/box.png");
                Test2.setCollider(false, 100, 100);
                allObject2D.add(Test2);
                allPhysicsObject.add(Test2);

        //Station station = new Station(1000,1000);

        ObjectGUI Button = new ObjectGUI("Button", 50, 300, 100, 100, "./assets/button.gif" );

        float playerSpeed = 1.5f;
        float camSpeed = 3f;

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


            for (Object2D object2D : allObject2D) {
                object2D.update();
            }

            /*
            Purpur.update();
            Test.update();
            Test2.update();
            */

            Purpur.checkClicked(mouse);

            //station.update(window);

            Button.checkClicked(mouse);

            collision.check(allPhysicsObject);

            renderer.renderObject(allObject2D);

            //renderer.renderObject(station.allStationParts);

            renderer.renderGUI(Button);

            glfwSwapBuffers(window.window);
        }

        shader.terminate();

        glfwTerminate();
    }
}