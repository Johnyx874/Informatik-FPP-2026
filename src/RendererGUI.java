import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class RendererGUI {

    Window window;
    Shader shader;

    public RendererGUI(Window window, Shader shader) {
        this.window = window;
        this.shader = shader;

        Log.print("Renderer2D for ObjectGUI created!");
    }

    public void renderGUI(ArrayList<ObjectGUI> objects) {
        for (ObjectGUI obj : objects) {
            renderGUI(obj);
        }
    }

    public void renderGUI(ObjectGUI object) {

        // Parameters to send to shader
        float renderX = object.position.x;
        float renderY = object.position.y;

        shader.bindGUI();

            object.texture.bind();

                glBindVertexArray(object.vao);

                shader.setVec2_GUI("u_ScreenSize", window.width, window.height);

                shader.setVec2_GUI("u_Position", renderX, renderY);

                glDrawArrays(GL_TRIANGLE_FAN, 0, 4);

            glBindVertexArray(0);

            object.texture.unbind();

        shader.unbind();
    }

}
