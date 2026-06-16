

public class PhysicsObject extends Object2D {

    public Collider collider;

    public PhysicsObject(String name, float x, float y, float width, float height, String texPath) {

        super(name, x, y, width, height, texPath);

        this.collider = new Collider(this, x, y, width, height, false);
    }

    public void setCollider(boolean isDynamic) {
        collider.isDynamic = isDynamic;
    }

    public void setCollider(boolean isDynamic, float width, float height) {
        collider.width = width;
        collider.height = height;
        collider.isDynamic = isDynamic;
    }

    @Override
    public void update() {
        collider.update(this.position);
    }



}
