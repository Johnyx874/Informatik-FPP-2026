public class Button extends ObjectGUI{

    Texture texNormal;
    Texture texHovered;
    Texture texPressed;

    public Button(String name, float x, float y, float width, float height, String texPath){

        super(name, x, y, width, height, "./assets/placeholder.png");

        texNormal = new Texture(texPath + "_normal.png");
        texHovered= new Texture(texPath + "_hovered.png");
        texPressed = new Texture(texPath + "_pressed.png");

        this.texture = texNormal;

    }
    public void switchToNormal(){
        this.texture = texNormal;
    }
    public void switchToHovered(){
        this.texture = texHovered;
    }
    public void switchToPressed(){
        this.texture = texPressed;
    }

}
