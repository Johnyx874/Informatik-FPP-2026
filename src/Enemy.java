import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_R;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import java.util.Random;


public class Enemy extends Entity {

    float movementSpeed;
    private Player player;

    private Texture idleTexture;
    private Texture pursueTexture;

    public Enemy (float x, float y, Player player, float width, float height, String texPath1, String texPath2) {

        super("enemy", x, y, width, height, texPath1);

        idleTexture = new Texture(texPath1);
        pursueTexture = new Texture(texPath2);

        movementSpeed = 25f;
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

    public void update(Input input, float deltaTime) {

        texture = idleTexture;

        if (playerIsInRadius()) { texture = pursueTexture; movePursue(); }
        else { moveIdle(); }

        checkDragged(input);

        super.update(deltaTime);

    }
}