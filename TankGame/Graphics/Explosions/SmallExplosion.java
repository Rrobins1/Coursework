package Graphics.Explosions;

import java.io.IOException;

public class SmallExplosion extends Explosion {
    public SmallExplosion( int x, int y ) throws IOException {
        resourcePath = "DankTank"+sep+"src" + sep + "Resources" + sep + "Visual" + sep + "Animations" + sep + "smallExplosion.png";
        numberXFrames = 6;
        numberYFrames = 1;
        frameDelay = 4;
        initializeSprite();
        drawExplosion( x, y );
    }
}
