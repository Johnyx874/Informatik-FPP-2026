import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13C.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13C.glActiveTexture;
import org.lwjgl.BufferUtils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Texture {
    private int texID;
    int width;
    int height;

    public Texture(String filename) {
        try {
            BufferedImage bi = ImageIO.read(new File(filename));
            if (bi == null) { throw new RuntimeException("Invalid image format: " + filename); }
            width = bi.getWidth();
            height = bi.getHeight();

            int[] pixels_raw = bi.getRGB(0, 0, width, height, null, 0, width);

            ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);

            for(int y = 0; y < height; y++) {
                for(int x = 0; x < width; x++) {
                    int pixel = pixels_raw[y * width + x];
                    pixels.put( (byte) ((pixel >> 16) & 0xFF) ); // RED
                    pixels.put( (byte) ((pixel >> 8) & 0xFF) );  // GREEN
                    pixels.put( (byte) ((pixel >> 0) & 0xFF) );  // BLUE
                    pixels.put( (byte) ((pixel >> 24) & 0xFF) ); // ALPHA
                }
            }

            pixels.flip();

            texID = glGenTextures();

            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, texID);

            // What happens when texture space is larger? Repeat Image (wrap):
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

            // What happens when texture space is stretched/shrinked? Stretch/Shrink Image (nearest = pixelated):
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST); // shrink
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST); // stretch

            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);



        }catch(IOException e) {
            throw new RuntimeException("Failed to load texture: " + filename);
        }

    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, texID);
    }
    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
