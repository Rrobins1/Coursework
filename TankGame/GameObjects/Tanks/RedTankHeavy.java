package GameObjects.Tanks;

import java.io.IOException;

public class RedTankHeavy extends Tank {
    public RedTankHeavy() throws IOException {
        resourcePath = "DankTank"+sep+"src" + sep + "Resources" + sep + "Visual" + sep + "Tanks" + sep + "Tank_red_heavy_strip60.png";
        health = 110;
        velocity = 3;
        projectileType = "Heavy";
        firingDelay = 1000;
        this.setBufferedImage();
    }

    public RedTankHeavy( double x, double y ) throws IOException {
        this();
        this.x = x;
        this.y = y;
        this.setHitBox();
        this.setSafeZone();
    }
}
