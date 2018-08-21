package GameObjects.Tanks;

import java.io.IOException;

public class BlueTankMedium extends Tank {
    public BlueTankMedium() throws IOException {
        resourcePath = "DankTank"+sep+"src" + sep + "Resources" + sep + "Visual" + sep + "Tanks" + sep + "Tank_blue_basic_strip60.png";
        health = 100;
        velocity = 3;
        projectileType = "Medium";
        firingDelay = 500;
        this.setBufferedImage();
    }

    public BlueTankMedium( double x, double y ) throws IOException {
        this();
        this.x = x;
        this.y = y;
        this.setHitBox();
        this.setSafeZone();
    }
}
