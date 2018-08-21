package Graphics;

import java.awt.*;

public class Animation {
    private Sprite sprite;

    private int x;
    private int y;

    private int frameCount;
    private int frameDelay;
    private int currentFrame;
    private int duration;

    private boolean loop;
    private boolean stop;

    public Animation( Sprite sprite, int x, int y, int frameDelay, boolean loop ) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.frameDelay = frameDelay;
        this.loop = loop;
        this.stop = false;
        this.duration = 0;
    }

    public boolean isStopped() {
        return stop;
    }
    public int totalTime() {
        return this.frameDelay * sprite.frameCount();
    }

    public void repaint( Graphics graphics ) {
        if ( !stop ) {
            duration++;
            frameCount++;

            if ( frameCount > frameDelay ) {
                frameCount = 0;
                stop = (duration > this.totalTime() && !loop);
                currentFrame = (currentFrame + 1) % sprite.frameCount();
            }

            graphics.drawImage( sprite.getFrame( currentFrame ), x, y, null );
        }
    }
}
