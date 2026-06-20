import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;

public class Player extends Entity {

    Texture texHelmetOn;
    Texture texHelmetOff;

    float movementSpeed;

    public Player(float x, float y) {

        super("Purpur", x, y, 100, 100, "./assets/purpur.png");

        texHelmetOn = new Texture("./assets/purpur.png");
        texHelmetOff = new Texture("./assets/purpur_no_helmet.png");

        textures.add(texHelmetOn);
        textures.add(texHelmetOff);

        movementSpeed = 3f;
    }

    private void move(Input input) {
        if (input.pressed(GLFW_KEY_A)) {
            position.x -= movementSpeed;
        }
        if (input.pressed(GLFW_KEY_D)) {
            position.x += movementSpeed;
        }
        if (isGrounded && input.pressed(GLFW_KEY_W)) {
            velocity.y -= movementSpeed;
        }
        //if (isGrounded && input.clicked(GLFW_KEY_SPACE)) {
            //velocity.y -= 5f;
        //}
        if (input.clicked(GLFW_KEY_SPACE)) {

            System.out.println(
                    "CLICKED  grounded=" + isGrounded
            );
        }
    }

    private void checkDragged(Input input) {
        if (input.mouse.leftHeld(this)) {
            position.x = input.mouse.position.x - width / 2f;
            position.y = input.mouse.position.y - height / 2f;

            velocity = new Vector2(0,0);
        }
    }

    public void update(Input input) {

        move(input);

        if (input.clicked(GLFW_KEY_R) && texture == texHelmetOn) {
            texture = texHelmetOff;
            Log.print("< AHHH! I CAN'T BREATHE!! >");

            movementSpeed = 1f;
        }
        else if (input.clicked(GLFW_KEY_R) && texture == texHelmetOff) {
            texture = texHelmetOn;
            Log.print("< Puh.. some air.. >");

            movementSpeed = 3f;
        }

        checkDragged(input);

        super.update();

    }

}
