import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.lwjgl.glfw.GLFW.*;
// SURPRISE mother fcker
import static org.lwjgl.glfw.GLFW.GLFW_KEY_R;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import java.util.Random;


public class Enemy extends Entity {




    float movementSpeed;
    private Player player;
    boolean playerIsInRadius = false;

    public Enemy (float x, float y, Player player) {

        super("enemy", x, y, 100, 100, "./assets/eyesaac.png");

        movementSpeed = 0.1F;
        this.player = player;
    }

    private void movePersue() {
        if (player != null) {
            if (position.x < player.position.x && player.position.x - position.x < 400  ) {

                velocity.x = movementSpeed;
                playerIsInRadius = true;
            } else if (position.x > player.position.x && player.position.x - position.x > -400){
                velocity.x = -movementSpeed;
                playerIsInRadius = true;
            }
            else{
                playerIsInRadius = false;
            }
        }
    }

    private void moveIdle() {
       velocity.x =0;



    }


    private void checkDragged(Input input) {
        if (input.mouse.leftHeld(this)) {
            position.x = input.mouse.position.x - width / 2f;
            position.y = input.mouse.position.y - height / 2f;

            velocity = new Vector2(0,0);
        }
    }

    public void update(Input input) {
        if ((-400 < (player.position.x - position.x)) || ((player.position.x - position.x) < 400)) {
            movePersue();
            update();
        }
        else{
            moveIdle();
        }
        checkDragged(input);

        super.update();

    }
}
