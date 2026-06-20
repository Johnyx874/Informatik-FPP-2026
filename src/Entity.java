public class Entity extends PhysicsObject {

    private int health;
    private boolean isAlive;

    public Entity(String name, float x, float y, float width, float height, String texPath) {
        super(name, x, y, width, height, texPath, true);
        health = 1;
        isAlive = true;
    }

    public int getHealth() { return health; }
    public void addHealth(int health) { this.health += health; }
    public void subtractHealth(int health) { this.health -= health; }
    public void setHealth(int health) { this.health = health; }

    public boolean isAlive() { return isAlive; }
    public void setAlive(boolean alive) { isAlive = alive; }

    @Override
    public void update() {
        if (health <= 0) { isAlive = false; }
        shouldDestroy = !isAlive;

        super.update();
    }
}
