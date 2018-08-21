package GameObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

abstract public class GameObject {
    protected static final String sep = System.getProperty( "file.separator" );

    protected double x;
    protected double y;

    protected Rectangle hitBox;
    protected int currentDegrees;
    protected String resourcePath;
    protected BufferedImage image;

    protected void setBufferedImage() throws IOException {
        image = ImageIO.read( new File( resourcePath ) );
    }

    public void setHitBox() {
        hitBox = new Rectangle( ( int ) this.x, ( int ) this.y, image.getWidth(), image.getHeight() );
    }

    public double getXCoordinate() {
        return this.x;
    }
    public void setXCoordinate( double x ) {
        this.x = x;
    }
    public double getYCoordinate() {
        return this.y;
    }
    public void setYCoordinate( double y ) {
        this.y = y;
    }
    public int getWidth() {
        return this.image.getWidth();
    }
    public int getHeight() {
        return this.image.getHeight();
    }
    public int getCurrentDegrees() {
        return this.currentDegrees;
    }
    public double getCurrentRadians() {
        return Math.toRadians( currentDegrees );
    }
    public Rectangle getHitBox() {
        return this.hitBox;
    }
    public boolean collisionOcurred( GameObject otherObject ) {
        return hitBox.intersects( otherObject.getHitBox() );
    }

    public void repaint( Graphics graphics ) {
        Graphics2D graphics2d = ( Graphics2D ) graphics;
        graphics2d.drawImage( image, ( int ) x, ( int ) y, null );
    }
}
