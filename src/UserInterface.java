import java.util.ArrayList;
import java.util.Objects;

public class UserInterface {

    RendererGUI renderer;
    ArrayList<Button> buttons = new ArrayList<>();
    ArrayList<ObjectGUI> objects = new ArrayList<>();

    public UserInterface(Window window, Shader shader) {

        renderer = new RendererGUI(window, shader);

    }

    public Button createButton(String name, float x, float y, float width, float height, String texPath) {

        Button obj =
                new Button(name, x, y, width, height, texPath);

        buttons.add(obj);
        objects.add(obj);

        Log.print("ObjectGUI '" + obj.name + "' created!");

        return obj;
    }

    public void cleanup() {
        objects.removeIf(obj -> obj.shouldDestroy);
    }

    public void updateObjects(Input input) {

        Event.leftButtonPressed = false;
        Event.rightButtonPressed = false;
        Event.middleButtonPressed = false;


        for (Button button : buttons) {

            button.switchToNormal();

            if (input.mouse.hovering(button)) {
                button.switchToHovered();
            }

            if (input.mouse.leftHeld(button)) {

                button.switchToPressed();

                if (button.name.equals("Left Button")) {
                    Event.leftButtonPressed = true;
                }
                else if (button.name.equals("Right Button")) {
                    Event.rightButtonPressed = true;
                }
                else if (button.name.equals("Middle Button")) {
                    Event.middleButtonPressed = true;
                }

            }

        }
    }

    public void render() {

        renderer.renderGUI(objects);
    }
}
