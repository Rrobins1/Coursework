package GameObjects.Tanks;

import java.io.IOException;

public class BlueTankHeavy extends Tank {
    public BlueTankHeavy() throws IOException {
        resourcePath = "DankTank"+sep+"src" + sep + "Resources" + sep + "Visual" + sep + "Tanks" + sep + "Tank_blue_heavy_strip60.png";
        health = 110;
        velocity = 3;
        projectileType = "Heavy";
        firingDelay = 1000;
        this.setBufferedImage();
    }

    public BlueTankHeavy( double x, double y ) throws IOException {
        this();
        this.x = x;
        this.y = y;
        this.setHitBox();
        this.setSafeZone();
    }
}
