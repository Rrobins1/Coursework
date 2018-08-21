package GameObjects.Walls;

import java.io.IOException;

public class Indestructable extends Wall {
    private Indestructable() throws IOException {
        resourcePath = "DankTank"+sep+"src" + sep + "Resources" + sep + "Visual" + sep + "Environment" + sep + "Blue_wall1.png";
        this.assignID();
        this.setBufferedImage();
        health = 1;
    }

    public Indestructable( int xCoordinate, int yCoordinate ) throws IOException {
        this();
        this.setXCoordinate( xCoordinate );
        this.setYCoordinate( yCoordinate );
        this.setHitBox();
    }
}
