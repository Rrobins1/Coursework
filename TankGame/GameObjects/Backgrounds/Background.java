

/**
 * Created by lorraine on 5/9/17.
 */
package GameObjects.Backgrounds;

        import GameObjects.GameObject;

        import java.io.IOException;

public class Background extends GameObject {
    public Background() throws IOException {
        resourcePath = "DankTank"+sep+"src" + sep + "Resources" + sep + "Visual" + sep + "Environment" + sep + "Background.png";
        this.setBufferedImage();
    }
}