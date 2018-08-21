package GameObjects.Walls;

import java.io.IOException;

public class Destructable extends Wall {
    private Destructable() throws IOException {
        resourcePath = "DankTank"+sep+"src" + sep + "Resources" + sep + "Visual" + sep + "Environment" + sep + "Blue_wall2.png";
        this.assignID();
        this.setBufferedImage();
        this.setHitBox();
        health = 1;
    }

    public Destructable( int xCoordinate, int yCoordinate ) throws IOException {
        this();
        this.setXCoordinate( xCoordinate );
        this.setYCoordinate( yCoordinate );
        this.setHitBox();
    }

    @Override
    public void takeDamage( int damage ) {
        health = -damage;
    }
}
