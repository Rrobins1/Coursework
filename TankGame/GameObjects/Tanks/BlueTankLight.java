package GameObjects.Tanks;

import java.io.IOException;

public class BlueTankLight extends Tank {
    public BlueTankLight() throws IOException {
        resourcePath = "DankTank"+sep+"src" + sep + "Resources" + sep + "Visual" + sep + "Tanks" + sep + "Tank_blue_light_strip60.png";
        health = 90;
        velocity = 4;
        projectileType = "Light";
        firingDelay = 200;
        this.setBufferedImage();
    }

    public BlueTankLight( double x, double y ) throws IOException {
        this();
        this.x = x;
        this.y = y;
        this.setHitBox();
        this.setSafeZone();
    }
}
