import java.util.ArrayList;

public class Animation_Old {

    ArrayList<Texture> textures = new ArrayList<>();

    ArrayList<Texture> facingLeft = new ArrayList<>();
    ArrayList<Texture> facingRight = new ArrayList<>();

    public Animation_Old() {
        for (int i = 0; i < 16; i++) {
            textures.add(null);
            facingLeft.add(null);
            facingRight.add(null);
        }
    }

    void assignIdle(String filename) {
        textures.set(0, new Texture(filename));
    }

    void assignLeft(String filename) {
        textures.set(1, new Texture(filename));
    }


    void assignFacingLeft(String idle, String slide1, String slide2, String slide3, String slide4) {
        facingLeft.set(0, new Texture(idle));
        facingLeft.set(1, new Texture(slide1));
        facingLeft.set(2, new Texture(slide2));
        facingLeft.set(3, new Texture(slide3));
        facingLeft.set(4, new Texture(slide4));
    }
    void assignFacingRight(String idle, String slide1, String slide2, String slide3, String slide4) {
        facingRight.set(0, new Texture(idle));
        facingRight.set(1, new Texture(slide1));
        facingRight.set(2, new Texture(slide2));
        facingRight.set(3, new Texture(slide3));
        facingRight.set(4, new Texture(slide4));
    }

    float timer = 0.0f;
    int counter = 1;
    Texture iterateAnimation(String arrayName, float deltaTime) {

        ArrayList<Texture> array = new ArrayList<>();

        if (arrayName.equals("left")) array = facingLeft;
        else if (arrayName.equals("right")) array = facingRight;

        timer += deltaTime;

        Texture tex = array.get(counter);
        if (timer >= 0.25f) {
            if (counter == 4) counter = 0;
            counter++;
            tex = array.get(counter);
            timer -= 0.25f;
            return tex;
        }
        return tex;
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

    /*
    float timer = 0.0f;
    void update(float deltaTime) {

        timer += deltaTime;

        if (timer >= 0.25f) {
            // animation switch to next slide



            timer -= 0.25f;

        }

    }
     */




}
