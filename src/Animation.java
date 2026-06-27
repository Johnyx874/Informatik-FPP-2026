import java.util.ArrayList;

public class Animation {

    private final ArrayList<Texture> textures = new ArrayList<>();

    private final int slides;
    private final float durationPerSlide;
    private final float duration;
    private float timer = 0.0f;
    private int state = 0;

    public Animation(int slides, float duration) {
        this.slides = slides;
        this.duration = duration;
        this.durationPerSlide = this.duration / slides;

    }

    void addSlide(String texPath) {
        textures.add(new Texture(texPath));
    }

    Texture update(float deltaTime) {

        timer += deltaTime;

        Texture tex = textures.get(state);
        if (timer >= durationPerSlide) {
            state++;
            if (state >= slides) state = 0;
            tex = textures.get(state);
            timer -= durationPerSlide;
            return tex;
        }
        return tex;
    }

    void resetState() {
        this.state = 0;
    }

}
