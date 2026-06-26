import javax.swing.*;
import java.util.ArrayList;

public class Scene {

    Renderer2D renderer;
    CollisionSystem collisionSystem;

    ArrayList<Entity> entities;
    ArrayList<PhysicsObject> physicsObjects;
    ArrayList<Object2D> allObjects;

    float gravity;

    public Scene(float gravity, Window window, Shader shader, Camera2D camera) {

        renderer = new Renderer2D(window, shader, camera);
        collisionSystem = new CollisionSystem();

        this.gravity = gravity;

        entities = new ArrayList<>();
        allObjects = new ArrayList<>();
        physicsObjects = new ArrayList<>();

        Log.print("Scene created!");
    }

    public Entity createEntity(String name, float x, float y, float width, float height, String texPath) {

        Entity obj =
                new Entity(name, x, y, width, height, texPath);

        entities.add(obj);
        physicsObjects.add(obj);
        allObjects.add(obj);

        Log.print("Entity '" + obj.name + "' created!");

        return obj;
    }

    public PhysicsObject createPhysicsObject(String name, float x, float y, float width, float height, String texPath,
                                             boolean isDynamic, float colliderWidth, float colliderHeight) {
        PhysicsObject obj =
                new PhysicsObject(name, x, y, width, height, texPath, isDynamic, colliderWidth, colliderHeight);

        physicsObjects.add(obj);
        allObjects.add(obj);

        Log.print("PhysicsObject '" + obj.name + "' created!");

        return obj;
    }

    public Object2D createObject2D(String name, float x, float y, float scale, String texPath) {

        Object2D obj =
                new Object2D(name, x, y, scale, texPath);

        allObjects.add(obj);

        Log.print("Object2D '" + obj.name + "' created!");

        return obj;
    }

    public void addPhysicsObject(PhysicsObject obj) {
        physicsObjects.add(obj);
        allObjects.add(obj);
    }

    public void cleanup() {

        entities.removeIf(obj -> obj.shouldDestroy);
        physicsObjects.removeIf(obj -> obj.shouldDestroy);
        allObjects.removeIf(obj -> obj.shouldDestroy);

    }

    public void applyGravity(float deltaTime) {
        for (PhysicsObject obj : physicsObjects) {

            if (obj.position.y >= 1000) {
                if (obj instanceof Player && Event.playerHasGravity) {
                    obj.isGrounded = true;
                    obj.position.y = 1000;
                    obj.velocity.y = 0;
                }
                else if (!(obj instanceof Player)) {
                    obj.isGrounded = true;
                    obj.position.y = 1000;
                    obj.velocity.y = 0;
                }
            }

            if (obj instanceof Player) {
                if (Event.playerHasGravity && !obj.isGrounded) {
                    obj.velocity.y += gravity * deltaTime;
                }
            }

            else if (obj.isDynamic && !obj.isGrounded) {
                obj.velocity.y += gravity * deltaTime;
            }
        }
    }


    public void updateObjects(float deltaTime) {
        for (Object2D object2D : allObjects) {
            object2D.update(deltaTime);
        }
    }


    public void updateCollision() {
        for (PhysicsObject obj : physicsObjects) { obj.isGrounded = false; }
        collisionSystem.check(physicsObjects);
    }

    public void render() {
        renderer.renderObject(allObjects);
    }


}
