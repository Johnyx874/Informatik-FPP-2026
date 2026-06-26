import java.util.ArrayList;

public class Animation {

    ArrayList<Texture> textures = new ArrayList<>();

    public Animation() {
        for (int i = 0; i < 16; i++) {
            textures.add(null);
        }
    }

    void assignIdle(String filename) {
        textures.set(0, new Texture(filename));
    }
    void assignLeft(String filename) {
        textures.set(1, new Texture(filename));
    }
    void assignRight(String filename) {
        textures.set(2, new Texture(filename));
    }
    void assignJump(String filename) {
        textures.set(3, new Texture(filename));
    }

    void assignOn(String filename) {
        textures.set(4, new Texture(filename));
    }
    void assignOff(String filename) {
        textures.set(5, new Texture(filename));
    }

    Texture getIdle() {
        return textures.get(0);
    }
    Texture getLeft() {
        return textures.get(1);
    }
    Texture getRight() {
        return textures.get(2);
    }
    Texture getJump() {
        return textures.get(3);
    }

    Texture getOn() {
        return textures.get(4);
    }
    Texture getOff() {
        return textures.get(5);
    }

    void setTexture(int x, String filename) {
        Texture tex = new Texture(filename);
        textures.set(x,tex);
    }
    Texture getTexture(int x, String filename) {
        return textures.get(x);
    }

    float timer = 0.0f;
    void update(float deltaTime) {

        timer += deltaTime;

        if (timer >= 1.0f) {
            timer -= 1.0f;

            //Log.print("1 Second");
        }

    }

}
