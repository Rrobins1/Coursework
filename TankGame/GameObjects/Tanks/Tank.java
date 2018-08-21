package GameObjects.Tanks;

import GameObjects.GameObject;
import GameObjects.Projectiles.HeavyProjectile;
import GameObjects.Projectiles.LightProjectile;
import GameObjects.Projectiles.MediumProjectile;
import GameObjects.Projectiles.Projectile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

abstract public class Tank extends GameObject {
    protected final int ROTATION_DEGREES = 1;

    protected int health;
    protected int velocity;
    protected String projectileType;
    protected double safeX;
    protected double safeY;
    protected double lastFired = 0;
    protected double firingDelay;

    public void takeDamage( int damageAmount ) {
        this.health = health - damageAmount;
    }
    public boolean isDead() {
        return health <= 0;
    }
    public int getHealth() {
        return health;
    }

    public void setSafeZone() {
        this.safeX = this.getXCoordinate();
        this.safeY = this.getYCoordinate();
    }

    public void placeAtSafeZone() {
        this.x = safeX;
        this.y = safeY;
        this.setHitBox();
    }

    public void forward() {
        x += velocity * Math.cos( this.getCurrentRadians() );
        y += velocity * Math.sin( this.getCurrentRadians() );
        setHitBox();
    }

    public void backward() {
        x -= velocity * Math.cos( this.getCurrentRadians() );
        y -= velocity * Math.sin( this.getCurrentRadians() );
        setHitBox();
    }

    public void rotateLeft() {
        this.rotate( false );
    }

    public void rotateRight() {
        this.rotate( true );
    }

    public void rotate( boolean positive ) {
        if ( positive ) currentDegrees = (currentDegrees + velocity * ROTATION_DEGREES) % 360;
        if ( !positive ) currentDegrees = (currentDegrees - velocity * ROTATION_DEGREES) % 360;
    }

    public boolean readyToFire() {
        return (System.currentTimeMillis() - lastFired) > firingDelay;
    }

    public Projectile fire() throws IOException {
        {
            lastFired = System.currentTimeMillis();
            switch ( projectileType ) {
                case "Light":
                    return new LightProjectile( this );
                case "Medium":
                    return new MediumProjectile( this );
                case "Heavy":
                    return new HeavyProjectile( this );
            }
        }
        return null;
    }

    @Override
    protected void setBufferedImage() throws IOException {
        image = ImageIO.read( new File( resourcePath ) );
        image = image.getSubimage( 0, 0, image.getWidth() / 60, image.getHeight() );
    }

    @Override
    public void repaint( Graphics graphics ) {
        AffineTransform at = AffineTransform.getTranslateInstance( 0, 0 );
        at.rotate( Math.toRadians( currentDegrees ), x + 32, y + 32 );
        Graphics2D graphics2d = ( Graphics2D ) graphics.create();
        graphics2d.rotate( Math.toRadians( currentDegrees ) );

        graphics2d.setTransform( at );
        graphics2d.drawImage( image, ( int ) x, ( int ) y, null );
    }
}
