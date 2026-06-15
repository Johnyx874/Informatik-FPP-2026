import java.util.ArrayList;

public class Collision {

    boolean separated;

    public void check(ArrayList<Object2D> objects) {

        for (int i = 0; i < objects.size(); i++) {

            Object2D a = objects.get(i);

            for (int j = i + 1; j < objects.size(); j++) {

                Object2D b = objects.get(j);

                separated =
                        (a.position.x + a.width  < b.position.x) ||
                        (a.position.x > b.position.x + b.width) ||
                        (a.position.y + a.height < b.position.y) ||
                        (a.position.y > b.position.y + b.height);

                if (!separated) {

                    System.out.println(
                            a.name + " collides with " + b.name
                    );

                    resolveCollision(a, b);
                }
            }
        }
    }

    public void resolveCollision(Object2D a, Object2D b) {

        if (!separated) {

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
                    if (!a.isStatic && b.isStatic) {
                        a.position.x -= overlapX;
                    }
                    else if (a.isStatic && !b.isStatic) {
                        b.position.x += overlapX;
                    }
                    else {
                        a.position.x -= overlapX / 2f;
                        b.position.x += overlapX / 2f;
                    }
                }
                else {

                    // a is right of b
                    if (!a.isStatic && b.isStatic) {
                        a.position.x += overlapX;
                    }
                    else if (a.isStatic && !b.isStatic) {
                        b.position.x -= overlapX;
                    }
                    else {
                        a.position.x += overlapX / 2f;
                        b.position.x -= overlapX / 2f;
                    }
                }

            }
            else {

                // Resolve Y

                if (a.position.y < b.position.y) {

                    // a is above b
                    if (!a.isStatic && b.isStatic) {
                        a.position.y -= overlapY;
                    }
                    else if (a.isStatic && !b.isStatic) {
                        b.position.y += overlapY;
                    }
                    else {
                        a.position.y -= overlapY / 2f;
                        b.position.y += overlapY / 2f;
                    }
                }
                else {

                    // a is below b
                    if (!a.isStatic && b.isStatic) {
                        a.position.y += overlapY;
                    }
                    else if (a.isStatic && !b.isStatic) {
                        b.position.y -= overlapY;
                    }
                    else {
                        a.position.y += overlapY / 2f;
                        b.position.y -= overlapY / 2f;
                    }
                }
            }
        }

    }

}