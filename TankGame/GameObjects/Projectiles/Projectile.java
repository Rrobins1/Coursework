package GameObjects.Projectiles;

import GameObjects.GameObject;
import GameObjects.Tanks.Tank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

abstract public class Projectile extends GameObject {
    protected String name;
    protected int damage;
    protected int velocity;
    protected Tank source;

    public void forward() {
        setHitBox();
        x += velocity * Math.cos( this.getCurrentRadians() );
        y += velocity * Math.sin( this.getCurrentRadians() );
    }

    public Tank getSource() {
        return source;
    }
    public int getDamage() {
        return damage;
    }
    public String getName() {
        return name;
    }

    @Override
    public void repaint( Graphics graphics ) {
        AffineTransform at = AffineTransform.getTranslateInstance( 0, 0 );
        at.rotate( Math.toRadians( currentDegrees ), x + image.getWidth() / 2, y + image.getHeight() / 2 );
        Graphics2D graphics2d = ( Graphics2D ) graphics.create();
        graphics2d.rotate( Math.toRadians( currentDegrees ) );
        graphics2d.setTransform( at );
        graphics2d.drawImage( image, ( int ) x, ( int ) y, null );
    }

    @Override
    protected void setBufferedImage() throws IOException {
        image = ImageIO.read( new File( resourcePath ) );
        image = image.getSubimage( 0, 0, image.getWidth() / 60, image.getHeight() );
    }
}
