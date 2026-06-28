import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {

    public static void main(String[] args) {

        Window window = new Window(1280, 720);
        Shader shader = new Shader(window);
        Camera2D camera = new Camera2D(1.5f, 770/2f, 450/2f, window);

        Scene scene = new Scene(1000f, window, shader, camera);
        UserInterface userInterface = new UserInterface(window, shader);
        Input input = new Input();



        // BACKGROUND
            scene.createObject2D("Grid",-1802,901, 1.0f, "./assets/grid.png");
            scene.createObject2D("Grid",-901,901, 1.0f, "./assets/grid.png");
            scene.createObject2D("Grid",0,901, 1.0f, "./assets/grid.png");
            scene.createObject2D("Grid",901,901, 1.0f, "./assets/grid.png");
            scene.createObject2D("Grid",1802,901, 1.0f, "./assets/grid.png");

            scene.createObject2D("Grid",-1802,0, 1.0f, "./assets/grid.png");
            scene.createObject2D("Grid",-901,0, 1.0f, "./assets/grid.png");
            scene.createObject2D("Grid",0,0, 1.0f, "./assets/grid.png");
            scene.createObject2D("Grid",901,0, 1.0f, "./assets/grid.png");
            scene.createObject2D("Grid",1802,0, 1.0f, "./assets/grid.png");

            scene.createObject2D("Grid",-1802,-901, 1.0f, "./assets/grid.png");
            scene.createObject2D("Grid",-901,-901, 1.0f, "./assets/grid.png");
            scene.createObject2D("Grid",0,-901, 1.0f, "./assets/grid.png");
            scene.createObject2D("Grid",901,-901, 1.0f, "./assets/grid.png");
            scene.createObject2D("Grid",1802,-901, 1.0f, "./assets/grid.png");

        // STATION
            scene.createPhysicsObject("High Left Box", 0,230, 230,1, "./assets/box.png",
                    false, 230, 20);
            scene.createPhysicsObject("High Middle Box", 350,230, 400,1, "./assets/box.png",
                    false, 400, 20);
            scene.createPhysicsObject("Low Box", 15,411, 745,1, "./assets/box.png",
                    false, 745, 1);
            scene.createObject2D("Station",0,0, 1.5f, "./assets/station.png");
        // ###


        // EYESAAC
            scene.createPhysicsObject("Eyesaac Island", 1400,500, 500,50, "./assets/metal_ground.png",
                    false, 500, 50);
            scene.createPhysicsObject("Left Fence", 1400,393, 45,107, "./assets/metal_fence_left.png",
                    false, 45, 107);
            scene.createPhysicsObject("Right Fence", 1855,393, 45,107, "./assets/metal_fence_right.png",
                    false, 45, 107);
        // ###


        // PURPUR
            Player Purpur = new Player(50, 50);
            scene.addPhysicsObject(Purpur);
        // ###


        // ENEMY
            Enemy Eyesaac = new Enemy(1700, 300, Purpur, 100, 100,
                    "./assets/eyesaac/eyesaac.png", "./assets/eyesaac/eyesaac_pursue.png");
            scene.addPhysicsObject(Eyesaac);
        // ###aaaa


        //scene.createPhysicsObject("Small Box", 0,0,100,100, "./assets/placeholder.png",
        //                        true, 100, 100);




        //userInterface.createButton("Left Button", 50, 300, 100, 100, "./assets/button");

        userInterface.createButton("Right Button", 1150, 300, 100, 100, "./assets/button");

        //userInterface.createButton("Middle Button", 540, 600, 100, 100, "./assets/button");

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

            glfwSwapBuffers(window.window);

        }

        shader.terminate();

        glfwTerminate();
    }
}