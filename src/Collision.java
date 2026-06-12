import java.util.ArrayList;

public class Collision {

    public void check(ArrayList<Object2D> objects) {

        for (int i = 0; i < objects.size(); i++) {

            Object2D a = objects.get(i);

            for (int j = i + 1; j < objects.size(); j++) {

                Object2D b = objects.get(j);

                boolean separated =
                        (a.position.x + a.width  < b.position.x) ||
                        (a.position.x > b.position.x + b.width) ||
                        (a.position.y + a.height < b.position.y) ||
                        (a.position.y > b.position.y + b.height);

                if (!separated) {

                    System.out.println(
                            a.name + " collides with " + b.name
                    );
                }
            }
        }
    }
}