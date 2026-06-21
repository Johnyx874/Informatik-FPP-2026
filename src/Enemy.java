import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_R;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import java.util.Random;


public class Enemy extends Entity {




    float movementSpeed;
    private Player player;

    public Enemy (float x, float y, Player player) {

        super("enemy", x, y, 100, 100, "./assets/eyesaac.png");

        movementSpeed = 0.1F;
        this.player = player;
    }

    private void movePersue() {
        if (player != null) {
            if (position.x < player.position.x && player.position.x - position.x < 400) {

                velocity.x = movementSpeed;

            } else if (position.x > player.position.x && player.position.x - position.x > -400){
                velocity.x = -movementSpeed;

            }

        }
    }

    private void moveIdle() {
        Random r= new Random();
       velocity.x= velocity.x + r.nextInt(2);
    }


    private void checkDragged(Input input) {
        if (input.mouse.leftHeld(this)) {
            position.x = input.mouse.position.x - width / 2f;
            position.y = input.mouse.position.y - height / 2f;

            velocity = new Vector2(0,0);
        }
    }

    public void update(Input input) {
        if (!input.pressed(GLFW_KEY_R)) {
            movePersue();
        }
        else{
            moveIdle();
        }
        checkDragged(input);

        super.update();

    }
}
