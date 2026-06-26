import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {

    public static void main(String[] args) {

        Window window = new Window(1280, 720);
        Shader shader = new Shader();
        Camera2D camera = new Camera2D(0, 0, window.width, window.height);

        Scene scene = new Scene(1000f, window, shader, camera);
        UserInterface userInterface = new UserInterface(window, shader);
        Input input = new Input();

        scene.createObject2D("Background",-300,-200, 2.5f, "./assets/space_1.jpg");

        Player Purpur = new Player(50, 50);
        scene.addPhysicsObject(Purpur);


        scene.createPhysicsObject("Small Box", 0,0,100,100, "./assets/placeholder.png",
                                true, 100, 100);

        scene.createPhysicsObject("Eyesaac", 800, 100, 200, 200, "./assets/eyesaac.png",
                                true, 200, 200);

        scene.createPhysicsObject("Big Box", 0,500, 1000,50, "./assets/placeholder.png",
                                false, 1000, 50);


        //userInterface.createButton("Left Button", 50, 300, 100, 100, "./assets/button");

        userInterface.createButton("Right Button", 1150, 300, 100, 100, "./assets/button");

        //userInterface.createButton("Middle Button", 540, 600, 100, 100, "./assets/button");

        userInterface.createSignalLight("Gravity Indicator", 1168, 225, 4, "./assets/led_on.png");

        long lastTime = System.nanoTime();

        while (!glfwWindowShouldClose(window.window)) {

            long currentTime = System.nanoTime();
            float deltaTime = (currentTime - lastTime) / 1_000_000_000.0f;
            lastTime = currentTime;

            //Log.print("" + deltaTime);




            glClear(GL_COLOR_BUFFER_BIT);
            glfwPollEvents();

            input.update(window, camera);

            camera.update(input, deltaTime);

            //Log.print(scene.physicsObjects.get(1).name + " " + scene.physicsObjects.get(1).isGrounded);

            scene.cleanup();
            scene.applyGravity(deltaTime);

            Purpur.update(input, deltaTime);

            //Purpur.position.x = input.mouse.position.x;
            //Purpur.position.y = input.mouse.position.y;

            Log.print("Purpur X: " + Purpur.position.x + "; Purpur Y: " + Purpur.position.y);

            Log.print("Maus X: " + input.mouse.position.x + "; Maus Y: " + input.mouse.position.y);

            scene.updateObjects(deltaTime);

            scene.updateCollision();
            scene.render();

            userInterface.cleanup();
            userInterface.updateObjects(input);
            userInterface.render();

            glfwSwapBuffers(window.window);

        }

        shader.terminate();

        glfwTerminate();
    }
}