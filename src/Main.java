import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Window window = new Window(1280, 720);
        Shader shader = new Shader(window);
        Camera2D camera = new Camera2D(1.5f, 300, -600, window);

        Scene scene = new Scene(1000f, window, shader, camera);
        UserInterface userInterface = new UserInterface(window, shader);
        Input input = new Input();

        // BACKGROUND
            scene.createObject2D("Big Grid",-2500 + 770/2f,-2500 + 450/2f, 1.0f, "./assets/big_grid.png");
        // ###

        // SPAWN ISLAND
            scene.createPhysicsObject("Spawn Island", -150,-500, 1000,50, "./assets/metal_ground.png",
                    false, 1000, 50);
            //scene.createPhysicsObject("Spawn Island", 500,-500, 500,50, "./assets/metal_ground.png",
            //        false, 500, 50);
        // ###

        // PURPUR
            Player Purpur = new Player(100, -650);
            scene.addPhysicsObject(Purpur);
        // ###



        userInterface.createButton("Right Button", 1150, 300, 100, 100, "./assets/button");

        userInterface.createSignalLight("Gravity Indicator", 1168, 225, 4, "./assets/led_on.png");

        long lastTime = System.nanoTime();

        while (!glfwWindowShouldClose(window.window)) {

            long currentTime = System.nanoTime();
            float deltaTime = (currentTime - lastTime) / 1_000_000_000.0f;
            lastTime = currentTime;

            glClear(GL_COLOR_BUFFER_BIT);
            glfwPollEvents();

            input.update(window, camera);

            camera.update(input, deltaTime);

            scene.cleanup();
            scene.applyGravity(deltaTime);

            Purpur.update(input, deltaTime);

            scene.updateObjects(deltaTime);

            scene.updateCollision();
            scene.render();

            userInterface.cleanup();
            userInterface.updateObjects(input);
            userInterface.render();

            Log.print("" + deltaTime);
            //serverCommunicator.sendValues(deltaTime);

            glfwSwapBuffers(window.window);

        }

        //pythonProcess.destroy();

        shader.terminate();

        glfwTerminate();
    }
}