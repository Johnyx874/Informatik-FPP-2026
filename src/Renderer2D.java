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

        Log.print("Renderer2D created!");
    }

    public void renderObject(ArrayList<Object2D> objects) {
        shader.bind2D();

        shader.setCameraPosition(camera.position.x, camera.position.y);
        shader.setCameraZoom(camera.zoom);

        for (Object2D obj : objects) {
            renderObject(obj);
        }

        shader.unbind();
    }

    public void renderObject(Object2D object) {

        // Parameters to send to shader
        float renderX = object.position.x;
        float renderY = object.position.y;

        object.texture.bind();

        glBindVertexArray(object.vao);

        shader.setVec2_2D("u_Position", renderX, renderY);

        glDrawArrays(GL_TRIANGLE_FAN, 0, 4);

    }

}
