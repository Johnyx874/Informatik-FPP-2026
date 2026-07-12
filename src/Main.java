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

        ServerCommunicator serverCommunicator = new ServerCommunicator();

        // BACKGROUND
            scene.createObject2D("Big Grid",-2500 + 770/2f,-2500 + 450/2f, 1.0f, "./assets/big_grid.png");
        // ###


        // STATION
            scene.createPhysicsObject("High Left Box", 0,230, 230,1, "./assets/box.png",
                    false, 230, 20);
            scene.createPhysicsObject("High Middle Box", 350,230, 400,1, "./assets/box.png",
                    false, 400, 20);
            scene.createPhysicsObject("Low Box", 15,411, 745,1, "./assets/box.png",
                    false, 745, 1);
            scene.createObject2D("Station",0,0, 1.5f, "./assets/station.png");
        // ###


        // EYESAAC ISLAND
            scene.createPhysicsObject("Eyesaac Island", 1400,500, 500,50, "./assets/metal_ground.png",
                    false, 500, 50);
            scene.createPhysicsObject("Left Fence", 1400,500-107, 45,107, "./assets/metal_fence_left.png",
                    false, 45, 107);
            scene.createPhysicsObject("Right Fence", 1400+500-45,500-107, 45,107, "./assets/metal_fence_right.png",
                    false, 45, 107);
        // ###


        // BOX ISLAND
            scene.createPhysicsObject("Box Island", -1200,300, 500,50, "./assets/metal_ground.png",
                    false, 500, 50);

            scene.createPhysicsObject("Small Box", -1000, 0, 100, 100, "./assets/box.png",
                    true, 100, 100);
            scene.createPhysicsObject("Small Box", -980, 0, 70, 70, "./assets/box.png",
                    true, 70, 70);
            scene.createPhysicsObject("Small Box", -870, 0, 50, 50, "./assets/box.png",
                    true, 50, 50);
        // ###


        // MECH ISLAND
            scene.createPhysicsObject("Mech Island", -800,1000, 500,50, "./assets/metal_ground.png",
                    false, 500, 50);
            scene.createPhysicsObject("Mech", -800,650, 217.5f,187.5f, "./assets/mech/mech.png",
                    true, 217.5f, 187.5f);
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


        // ENEMY EYESAAC
            Enemy Eyesaac = new Enemy(1700, 300, Purpur, 100, 100,
                    "./assets/eyesaac/eyesaac.png", "./assets/eyesaac/eyesaac_pursue.png");
            scene.addPhysicsObject(Eyesaac);
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

            Eyesaac.update(input, deltaTime);

            scene.updateObjects(deltaTime);

            scene.updateCollision();
            scene.render();

            userInterface.cleanup();
            userInterface.updateObjects(input);
            userInterface.render();

            Log.print("" + deltaTime);
            serverCommunicator.sendValues(deltaTime);

            glfwSwapBuffers(window.window);

        }

        shader.terminate();

        glfwTerminate();
    }
}