import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Renderer2D {

    Window window;
    Shader shader;
    Camera2D camera;

    public Renderer2D(Window window, Shader shader, Camera2D camera) {
        this.window = window;
        this.shader = shader;
        this.camera = camera;

        System.out.println("Renderer2D created!");
    }

    public void renderAllObjects(ArrayList<Object2D> objects) {
        for (Object2D obj : objects) {
            render(obj);
        }
    }

    public void render(Object2D object) {

        // Position relative to camera
        Vector2 camPos = camera.worldToCamera(object.position);

        // Parameters to send to shader
        float renderX = camPos.x;
        float renderY = camPos.y;

        shader.bind();

            object.texture.bind();

            glBindVertexArray(object.vao);

                shader.setVec2("u_ScreenSize", window.width, window.height);

                shader.setVec2("u_Position", renderX, renderY);

                glDrawArrays(GL_TRIANGLE_FAN, 0, 4);

            glBindVertexArray(0);

            object.texture.unbind();

        shader.unbind();
    }

}
