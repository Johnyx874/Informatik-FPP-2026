import java.util.ArrayList;

public class UserInterface {

    RendererGUI renderer;
    ArrayList<ObjectGUI> objects = new ArrayList<>();

    public UserInterface(Window window, Shader shader) {

        renderer = new RendererGUI(window, shader);

    }

    public ObjectGUI createObjectGUI(String name, float x, float y, float width, float height, String texPath) {

        ObjectGUI obj =
                new ObjectGUI(name, x, y, width, height, texPath);

        objects.add(obj);

        Log.print("ObjectGUI '" + obj.name + "' created!");

        return obj;
    }

    public void cleanup() {
        objects.removeIf(obj -> obj.shouldDestroy);
    }

    public void updateObjects() {
        for (ObjectGUI obj : objects) {
            obj.update();
        }
    }

    public void render() {

        renderer.renderGUI(objects);
    }
}
