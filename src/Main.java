import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {

    public static void main(String[] args) {

        Window window = new Window(1280, 720);
        Shader shader = new Shader();
        Camera2D camera = new Camera2D(0, 0);

        Scene scene = new Scene(0.01f, window, shader, camera);
        UserInterface userInterface = new UserInterface(window, shader);
        Input input = new Input();

        scene.createObject2D("Background",-300,-200, 2.5f, "./assets/space_1.jpg");

        Player Purpur = new Player(50, 50);
        Purpur.texture = Purpur.texHelmetOn;
        scene.addPhysicsObject(Purpur);


        scene.createPhysicsObject("Small Box", 0,0,100,100, "./assets/placeholder.png",
                                true, 100, 100);

        scene.createPhysicsObject("Big Box", 0,500, 1000,50, "./assets/placeholder.png",
                                false, 1000, 50);


        userInterface.createButton("Left Button", 50, 300, 100, 100, "./assets/button");

        userInterface.createButton("Right Button", 1150, 300, 100, 100, "./assets/button");

        userInterface.createButton("Middle Button", 540, 600, 100, 100, "./assets/button");


        while (!glfwWindowShouldClose(window.window)) {

            glClear(GL_COLOR_BUFFER_BIT);
            glfwPollEvents();

            input.update(window, camera);

            camera.update(input);



            scene.cleanup();
            scene.applyGravity();

            Purpur.update(input);
            scene.updateObjects();

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