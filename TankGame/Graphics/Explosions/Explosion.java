package Graphics.Explosions;

import Graphics.Animation;
import Graphics.Sprite;

import java.awt.*;
import java.io.IOException;

public class Explosion {
    protected static final String sep = System.getProperty( "file.separator" );

    protected String resourcePath;
    protected Sprite explosion;
    protected Animation animatedExplosion;
    protected int frameDelay;
    protected int numberXFrames, numberYFrames;

    protected void initializeSprite() throws IOException {
        this.explosion = new Sprite( resourcePath, numberXFrames, numberYFrames );
    }

    public void drawExplosion( int x, int y ) {
        animatedExplosion = new Animation( explosion, x - explosion.getXstep() / 2,
                y - explosion.getYstep() / 2, frameDelay, false );
    }

    public boolean isStopped() {
        return animatedExplosion.isStopped();
    }
    public void repaint( Graphics graphics ) {
        animatedExplosion.repaint( graphics );
    }
}
