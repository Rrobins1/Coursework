package Graphics.Explosions;

import java.io.IOException;

public class MediumExplosion extends Explosion {
    public MediumExplosion( int x, int y ) throws IOException {
        resourcePath = "DankTank"+sep+"src" + sep + "Resources" + sep + "Visual" + sep + "Animations" + sep + "mediumExplosion.png";
        numberXFrames = 4;
        numberYFrames = 4;
        frameDelay = 4;
        initializeSprite();
        drawExplosion( x, y );
    }
}
