import javax.swing.*;
import java.util.ArrayList;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {

    public static void main(String[] args) {

        Window window = new Window(1280, 720);
        Shader shader = new Shader();
        Camera2D camera = new Camera2D(0, 0);
        Scene scene = new Scene(0.1f, window, shader, camera);
        Input input = new Input();


        scene.createObject2D("Background",0,-350, 4.0f, "./assets/planet_test.png");

        Entity Purpur = new Entity("Purpur", 50, 50, 100, 100, "./assets/purpur.png");
        scene.addPhysicsObject(Purpur);


        scene.createPhysicsObject("Small Box", 0,-150,100,100, "./assets/box.png",
                                true, 100, 100);

        scene.createPhysicsObject("Big Box", 0,500, 1000,50, "./assets/box.png",
                                false, 1000, 50);

        ObjectGUI Button = new ObjectGUI("Button", 50, 300, 100, 100, "./assets/button.gif" );

        float playerSpeed = 1.5f;
        float camSpeed = 3f;

        while (!glfwWindowShouldClose(window.window)) {

            glClear(GL_COLOR_BUFFER_BIT);
            glfwPollEvents();

            input.update(window, camera);


            if (input.pressed(GLFW_KEY_A)) {
                Purpur.position.x -= playerSpeed;
            }

            if (input.pressed(GLFW_KEY_D)) {
                Purpur.position.x += playerSpeed;
            }

            if (input.pressed(GLFW_KEY_W)) {
                Purpur.position.y -= playerSpeed;
            }

            if (input.pressed(GLFW_KEY_S)) {
                Purpur.position.y += playerSpeed;
            }


            if (input.pressed(GLFW_KEY_LEFT)) {
                camera.position.x -= camSpeed;
            }

            if (input.pressed(GLFW_KEY_RIGHT)) {
                camera.position.x += camSpeed;
            }

            if (input.pressed(GLFW_KEY_UP)) {
                camera.position.y -= camSpeed;
            }

            if (input.pressed(GLFW_KEY_DOWN)) {
                camera.position.y += camSpeed;
            }

            //camera.position.x = Purpur.position.x - (window.width / 2);
            //camera.position.y = Purpur.position.y - (window.height / 2);


            if (input.mouse.leftHeld(Purpur)) {
                Purpur.position.x = input.mouse.position.x - Purpur.width / 2f;
                Purpur.position.y = input.mouse.position.y - Purpur.height / 2f;

                Purpur.velocity = new Vector2(0,0);
            }

            if (Purpur.isGrounded && input.pressed(GLFW_KEY_SPACE)) {

                Purpur.velocity.y -= 3f;
            }



            scene.cleanup();
            scene.applyGravity();
            scene.updateObjects();
            scene.updateCollision();
            scene.render();


            glfwSwapBuffers(window.window);
        }

        shader.terminate();

        glfwTerminate();
    }
}