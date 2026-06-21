import static org.lwjgl.glfw.GLFW.*;

public class Player extends Entity {


    Animation animation = new Animation();

    float movementSpeed;

    public Player(float x, float y) {

        super("Purpur", x, y, 100, 100, "./assets/purpur/purpur.png");

        animation.assignIdle("./assets/purpur/purpur_idle.png");
        animation.assignLeft("./assets/purpur/purpur_left.png");
        animation.assignRight("./assets/purpur/purpur_right.png");
        animation.assignJump("./assets/purpur/purpur_jump.png");

        movementSpeed = 500f;
    }

    private void move(Input input) {

        texture = animation.getIdle();
        velocity.x = 0;

        if (input.pressed(GLFW_KEY_A)) {
            velocity.x = -movementSpeed;
            texture = animation.getLeft();
        }
        if (input.pressed(GLFW_KEY_D)) {
            velocity.x = movementSpeed;
            texture = animation.getRight();
        }
        /*
        if (isGrounded && input.pressed(GLFW_KEY_W)) {
            velocity.y -= movementSpeed;
        }
        */
        if (isGrounded && input.clicked(GLFW_KEY_SPACE)) {
            velocity.y -= 500f;
        }

        if (!isGrounded) { texture = animation.getJump(); }

    }

    private void buttonMove() {
        if (Event.leftButtonPressed) {
            velocity.x = -movementSpeed;
        }
        else if (Event.rightButtonPressed) {
            velocity.x = movementSpeed;
        }
        else if (Event.middleButtonPressed && isGrounded) {
            velocity.y -= 1f;
        }
        else {
            velocity.x = 0;
        }
    }

    private void checkDragged(Input input) {
        if (input.mouse.leftHeld(this)) {
            position.x = input.mouse.position.x - width / 2f;
            position.y = input.mouse.position.y - height / 2f;

            velocity = new Vector2(0,0);
        }
    }

    @Override
    public void update(float deltaTime) {}

    public void update(Input input, float deltaTime) {

        move(input);

        //buttonMove();

        /*
        if (input.clicked(GLFW_KEY_R) && texture == texHelmetOn) {
            texture = texHelmetOff;
            Log.print("< AHHH! I CAN'T BREATHE!! >");

            movementSpeed = 1f;
        }
        else if (input.clicked(GLFW_KEY_R) && texture == texHelmetOff) {
            texture = texHelmetOn;
            Log.print("< Puh.. some air.. >");

            movementSpeed = 2f;
        }
         */

        checkDragged(input);

        super.update(deltaTime);

    }



}
