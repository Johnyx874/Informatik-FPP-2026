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
        Input input = new Input();

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

        PhysicsObject SmallBox = new PhysicsObject
                ("Small Box", 0,-150,100,100, "./assets/box.png");
                SmallBox.setCollider(true);
                allObject2D.add(SmallBox);
                allPhysicsObject.add(SmallBox);

        PhysicsObject BigBox = new PhysicsObject
                ("Big Box", 0,500, 1000,50, "./assets/box.png");
                BigBox.setCollider(false);
                allObject2D.add(BigBox);
                allPhysicsObject.add(BigBox);

        //Station station = new Station(1000,1000);

        ObjectGUI Button = new ObjectGUI("Button", 50, 300, 100, 100, "./assets/button.gif" );

        float playerSpeed = 1.5f;
        float camSpeed = 3f;

        while (!glfwWindowShouldClose(window.window)) {
            glfwPollEvents();

            glClear(GL_COLOR_BUFFER_BIT);

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



            input.update(window, camera);


            if (input.mouse.leftHeld(Purpur)) {
                Purpur.position.x = input.mouse.position.x - Purpur.width / 2f;
                Purpur.position.y = input.mouse.position.y - Purpur.height / 2f;

                Purpur.velocity = new Vector2(0,0);
            }


            if (!Purpur.isGrounded) {
                Purpur.velocity.y += 0.1f;
            }

            if (Purpur.isGrounded && input.pressed(GLFW_KEY_SPACE)) {

                Purpur.velocity.y -= 3f;
            }

            for (Object2D object2D : allObject2D) {
                object2D.update();
            }

            Purpur.isGrounded = false;

            collision.check(allPhysicsObject);



            renderer.renderObject(allObject2D);





            glfwSwapBuffers(window.window);
        }

        shader.terminate();

        glfwTerminate();
    }
}