

public class PhysicsObject extends Object2D {

    public Vector2 velocity;
    public Collider collider;
    public boolean isGrounded;
    public boolean isDynamic;

    public PhysicsObject(String name, float x, float y, float width, float height, String texPath,
                         boolean isDynamic) {

        super(name, x, y, width, height, texPath);

        this.velocity = new Vector2();
        this.isDynamic = isDynamic;
        this.collider = new Collider(this, x, y, width, height, this.isDynamic);
        this.isGrounded = false;
    }

    public PhysicsObject(String name, float x, float y, float width, float height, String texPath,
                         boolean isDynamic, float colliderWidth, float colliderHeight) {

        super(name, x, y, width, height, texPath);

        this.velocity = new Vector2();
        this.isDynamic = isDynamic;
        this.collider = new Collider(this, x, y, colliderWidth, colliderHeight, this.isDynamic);
        this.isGrounded = false;
    }

    @Override
    public void update() {

        position = position.add(velocity);
        collider.position.x = position.x;
        collider.position.y = position.y;

        System.out.println(name + " " + velocity.y);

    }



}
