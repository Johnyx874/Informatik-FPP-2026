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

        Player Purpur = new Player(50, 50);
        Enemy enemy= new Enemy(60,60, Purpur);

        Purpur.texture = Purpur.texHelmetOn;
        scene.addPhysicsObject(Purpur);
        scene.addPhysicsObject(enemy);


        scene.createPhysicsObject("Small Box", 0,-150,100,100, "./assets/box.png",
                                true, 100, 100);

        scene.createPhysicsObject("Big Box", 0,500, 1000,50, "./assets/box.png",
                                false, 1000, 50);

        ObjectGUI Button = new ObjectGUI("Button", 50, 300, 100, 100, "./assets/button.gif" );


        while (!glfwWindowShouldClose(window.window)) {

            glClear(GL_COLOR_BUFFER_BIT);
            glfwPollEvents();

            input.update(window, camera);

            camera.update(input);

            scene.cleanup();
            scene.applyGravity();

            Purpur.update(input);
            enemy.update(input);
            scene.updateObjects();

            scene.updateCollision();
            scene.render();

            glfwSwapBuffers(window.window);

        }

        shader.terminate();

        glfwTerminate();
    }
}