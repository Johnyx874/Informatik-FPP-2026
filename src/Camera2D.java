public class Camera2D {

    public Vector2 position;

    public float width;
    public float height;

    public Camera2D(float x, float y) {
        this.position = new Vector2(x, y);

        System.out.println("Camera2D created!");
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }

    public Vector2 worldToCamera(Vector2 worldPos) {

        float camX = worldPos.x - position.x + width / 2f;
        float camY = worldPos.y - position.y + height / 2f;

        return new Vector2(camX, camY);
    }
}