

public class PhysicsObject extends Object2D {

    public Vector2 velocity;
    public Collider collider;
    public boolean isGrounded;

    public PhysicsObject(String name, float x, float y, float width, float height, String texPath) {

        super(name, x, y, width, height, texPath);

        this.velocity = new Vector2();
        this.collider = new Collider(this, x, y, width, height, false);
        this.isGrounded = false;
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




        position = position.add(velocity);
        collider.update(this.position);



        System.out.println(name + " " + velocity.y);
    }



}
