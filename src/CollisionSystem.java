import java.util.ArrayList;

public class CollisionSystem {

    public void check(ArrayList<PhysicsObject> objects) {

        for (int i = 0; i < objects.size(); i++) {

            Collider a = objects.get(i).collider;

            for (int j = i + 1; j < objects.size(); j++) {

                Collider b = objects.get(j).collider;

                boolean separated =
                        (a.position.x + a.width  < b.position.x) ||
                        (a.position.x > b.position.x + b.width) ||
                        (a.position.y + a.height < b.position.y) ||
                        (a.position.y > b.position.y + b.height);

                if (!separated) {

                    System.out.println(
                            objects.get(i).name + " collides with " + objects.get(j).name
                    );

                    resolveCollision(a, b);
                }
            }
        }
    }

    public void resolveCollision(Collider a, Collider b) {

        if (a.isGhost || b.isGhost) {return;}

        float overlapX =
                Math.min(
                a.position.x + a.width,
                b.position.x + b.width
                )
                -
                Math.max(
                a.position.x,
                b.position.x
                );

        float overlapY =
                Math.min(
                a.position.y + a.height,
                b.position.y + b.height
                )
                -
                Math.max(
                        a.position.y,
                        b.position.y
                );


        //Resolve along smallest overlap axis

        if (overlapX < overlapY) {

            // Resolve X

            if (a.position.x < b.position.x) {

                // a is left of b
                if (a.isDynamic && !b.isDynamic) {
                    a.owner.position.x -= overlapX;
                }
                else if (!a.isDynamic && b.isDynamic) {
                    b.owner.position.x += overlapX;
                }
                else {
                    a.owner.position.x -= overlapX / 2f;
                    b.owner.position.x += overlapX / 2f;
                }
            }
            else {

                // a is right of b
                if (a.isDynamic && !b.isDynamic) {
                    a.owner.position.x += overlapX;
                }
                else if (!a.isDynamic && b.isDynamic) {
                    b.owner.position.x -= overlapX;
                }
                else {
                    a.owner.position.x += overlapX / 2f;
                    b.owner.position.x -= overlapX / 2f;
                }
            }

        }
        else {

            // Resolve Y

            if (a.position.y < b.position.y) {

                // a is above b

                if (a.isDynamic && !b.isDynamic) {

                    a.owner.position.y -= overlapY;

                    if (a.owner.velocity.y > 0) {
                        a.owner.velocity.y = 0;
                        a.owner.isGrounded = true;
                    }
                }
                else if (!a.isDynamic && b.isDynamic) {

                    b.owner.position.y += overlapY;

                    if (b.owner.velocity.y < 0) {
                        b.owner.velocity.y = 0;
                    }
                }
                else {

                    a.owner.position.y -= overlapY / 2f;
                    b.owner.position.y += overlapY / 2f;

                    if (a.owner.velocity.y > 0) {
                        a.owner.velocity.y = 0;
                        a.owner.isGrounded = true;
                    }

                    if (b.owner.velocity.y < 0) {
                        b.owner.velocity.y = 0;
                    }
                }

            }
            else {

                // a is below b

                if (a.isDynamic && !b.isDynamic) {

                    a.owner.position.y += overlapY;

                    if (a.owner.velocity.y < 0) {
                        a.owner.velocity.y = 0;
                    }
                }
                else if (!a.isDynamic && b.isDynamic) {

                    b.owner.position.y -= overlapY;

                    if (b.owner.velocity.y > 0) {
                        b.owner.velocity.y = 0;
                        b.owner.isGrounded = true;
                    }
                }
                else {

                    a.owner.position.y += overlapY / 2f;
                    b.owner.position.y -= overlapY / 2f;

                    if (a.owner.velocity.y < 0) {
                        a.owner.velocity.y = 0;
                    }

                    if (b.owner.velocity.y > 0) {
                        b.owner.velocity.y = 0;
                        b.owner.isGrounded = true;
                    }
                }
            }
        }
    }

}