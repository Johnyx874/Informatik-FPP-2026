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

    public void renderAllObject2D(ArrayList<Object2D> objects) {
        for (Object2D obj : objects) {
            renderObject2D(obj);
        }
    }

    public void renderObject2D(Object2D object) {

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

    public void renderAllObjectGUI(ArrayList<ObjectGUI> objects) {
        for (ObjectGUI obj : objects) {
            renderObjectGUI(obj);
        }
    }

    public void renderObjectGUI(ObjectGUI object) {

        // Parameters to send to shader
        float renderX = object.position.x;
        float renderY = object.position.y;

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
