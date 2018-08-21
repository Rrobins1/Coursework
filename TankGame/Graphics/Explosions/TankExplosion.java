package Graphics.Explosions;

import java.io.IOException;

public class TankExplosion extends Explosion {
    public TankExplosion( int x, int y ) throws IOException {
        resourcePath = "DankTank"+sep+"src" + sep + "Resources" + sep + "Visual" + sep + "Animations" + sep + "tankExplosion.png";
        numberXFrames = 5;
        numberYFrames = 5;
        frameDelay = 4;
        initializeSprite();
        drawExplosion( x, y );
    }
}
