public class Camera2D {

    private Vector2 position;

    private float width;
    private float height;

    public Camera2D(float x, float y, float width, float height) {
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }

    public void move(float dx, float dy) {
        position.x += dx;
        position.y += dy;
    }

    public Vector2 worldToCamera(Vector2 worldPos) {

        float camX = worldPos.x - position.x + width / 2f;
        float camY = worldPos.y - position.y + height / 2f;

        return new Vector2(camX, camY);
    }
}