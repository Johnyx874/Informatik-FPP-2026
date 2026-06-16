
public class Collider {

    PhysicsObject owner;

    Vector2 position;

    float width;
    float height;

    boolean isDynamic;

    Collider(PhysicsObject owner, float x, float y, float width, float height, boolean isDynamic) {

        this.owner = owner;

        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.isDynamic = isDynamic;

    }

    void update(Vector2 position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }

}
