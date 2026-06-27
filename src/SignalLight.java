public class SignalLight extends ObjectGUI{

    Animation_Old animation;

    public SignalLight(String name, float x, float y, float scale, String texPath) {

        super(name, x, y, scale, texPath);

        animation = new Animation_Old();

        animation.assignOn("./assets/led_on.png");
        animation.assignOff("./assets/led_off.png");

    }

    @Override
    public void update() {
        if (Event.playerHasGravity) {
            texture = animation.getOn();
        }
        else {
            texture = animation.getOff();
        }
    }

}
