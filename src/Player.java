import static org.lwjgl.glfw.GLFW.*;

public class Player extends Entity {

    Animation animationLeft = new Animation(4, 1f);
    Animation animationRight = new Animation(4, 1f);

    Texture idleTexture;
    Texture jumpTexture;

    float movementSpeed;

    public Player(float x, float y) {

        super("Purpur", x, y, 100, 100, "./assets/purpur/purpur.png");

        idleTexture = new Texture("./assets/purpur/purpur_idle.png");
        jumpTexture = new Texture("./assets/purpur/purpur_jump.png");

        String path = "./assets/animation test/";
        animationLeft.addSlide(path + "slide_1.png");
        animationLeft.addSlide(path + "slide_2.png");
        animationLeft.addSlide(path + "slide_3.png");
        animationLeft.addSlide(path + "slide_4.png");

        animationRight.addSlide(path + "slide_1.png");
        animationRight.addSlide(path + "slide_2.png");
        animationRight.addSlide(path + "slide_3.png");
        animationRight.addSlide(path + "slide_4.png");

        movementSpeed = 500f;
    }

    private void move(Input input, float deltaTime) {

        texture = idleTexture;
        velocity.x = 0;
        if (!Event.playerHasGravity) { velocity.y = 0; }

        if (input.pressed(GLFW_KEY_A)) {
            velocity.x = -movementSpeed;
            if (input.clicked(GLFW_KEY_A)) animationLeft.resetState();
            texture = animationLeft.update(deltaTime);
        }
        if (input.pressed(GLFW_KEY_D)) {
            velocity.x = movementSpeed;
            if (input.clicked(GLFW_KEY_D)) animationRight.resetState();
            texture = animationRight.update(deltaTime);
        }
        if (input.pressed(GLFW_KEY_W) && !Event.playerHasGravity) {
            velocity.y = -movementSpeed;
        }
        if (input.pressed(GLFW_KEY_S) && !Event.playerHasGravity) {
            velocity.y = movementSpeed;
        }

        if (isGrounded && input.clicked(GLFW_KEY_SPACE)) {
            velocity.y -= movementSpeed;
        }

        if (!isGrounded) { texture = jumpTexture; }

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

        move(input, deltaTime);

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

        //animation.update(deltaTime);

        //this.texture = animation.iterateFacingLeft(deltaTime);

        super.update(deltaTime);

    }



}
