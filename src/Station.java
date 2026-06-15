import java.util.ArrayList;
import static org.lwjgl.glfw.GLFW.*;

public class Station {

    public Vector2 position;

    private Object2D StationCore;
    private Object2D StationTop;
    private Object2D StationLeft;
    private Object2D StationBottom;
    private Object2D StationRight;

    private float size;

    public ArrayList<Object2D> allStationParts;

    public Station(float x, float y) {

        this.position = new Vector2(x,y);

        allStationParts = new ArrayList<>();

        size = 150f;

        StationCore = new Object2D("Station Core", true, this.position.x, this.position.y,
                size, size, "./assets/station/station_core.png" );
        StationTop = new Object2D("Station Top", true, this.position.x, this.position.y,
                size, size, "./assets/station/station_top.png" );
        StationLeft = new Object2D("Station Left", true, this.position.x, this.position.y,
                size, size, "./assets/station/station_left.png" );
        StationBottom = new Object2D("Station Bottom", true, this.position.x, this.position.y,
                size, size, "./assets/station/station_bottom.png" );
        StationRight = new Object2D("Station Right", true, this.position.x, this.position.y,
                size, size, "./assets/station/station_right.png" );

        allStationParts.add(StationCore);
        allStationParts.add(StationTop);
        allStationParts.add(StationLeft);
        allStationParts.add(StationBottom);
        allStationParts.add(StationRight);
    }

    private void updateStationParts() {

        StationCore.position.x = position.x;
        StationCore.position.y = position.y;

        StationTop.position.x = position.x;
        StationTop.position.y = position.y - size;

        StationLeft.position.x = position.x - size;
        StationLeft.position.y = position.y;

        StationBottom.position.x = position.x;
        StationBottom.position.y = position.y + size;

        StationRight.position.x = position.x + size;
        StationRight.position.y = position.y;

    }

    public void update(Window window) {

        if (glfwGetKey(window.window, GLFW_KEY_LEFT) == GLFW_PRESS) {
            position.x -= 3;
        }

        if (glfwGetKey(window.window, GLFW_KEY_RIGHT) == GLFW_PRESS) {
            position.x += 3;
        }

        if (glfwGetKey(window.window, GLFW_KEY_UP) == GLFW_PRESS) {
            position.y -= 3;
        }

        if (glfwGetKey(window.window, GLFW_KEY_DOWN) == GLFW_PRESS) {
            position.y += 3;
        }

        updateStationParts();

    }

}
