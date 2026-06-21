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

    public Enemy (float x, float y, Player player) {

        super("enemy", x, y, 100, 100, "./assets/eyesaac.png");

        movementSpeed = 0.1F;
        this.player = player;
    }

    private void checkDragged(Input input) {
        if (input.mouse.leftHeld(this)) {
            position.x = input.mouse.position.x - width / 2f;
            position.y = input.mouse.position.y - height / 2f;

            velocity = new Vector2(0,0);
        }
    }

    private boolean playerIsInRadius() {
        if (player == null) {return false;}

        float xDistance = Math.abs(player.position.x - position.x);
        float yDistance = Math.abs(player.position.y - position.y);

        return Math.sqrt((xDistance * xDistance) + (yDistance * yDistance)) < 400;
    }

    private void movePursue() {
        if (player == null) { return; }

        if (position.x < player.position.x) {
            velocity.x = movementSpeed;
        }
        else if (position.x > player.position.x){
            velocity.x = -movementSpeed;
        }
    }

    private void moveIdle() {
        velocity.x = 0;
    }

    public void update(Input input) {

        if (playerIsInRadius()) { movePursue(); }
        else { moveIdle(); }

        checkDragged(input);

        super.update();

    }
}
