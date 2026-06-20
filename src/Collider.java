
public class Collider {

    PhysicsObject owner;

    Vector2 position;

    float width;
    float height;

    boolean isDynamic;
    boolean isGhost;

    Collider(PhysicsObject owner, float x, float y, float width, float height, boolean isDynamic) {

        this.owner = owner;

        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.isDynamic = isDynamic;
        this.isGhost = false;

    }

}
