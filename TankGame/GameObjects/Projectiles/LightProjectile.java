package GameObjects.Projectiles;

import GameObjects.Tanks.Tank;

import java.io.IOException;

public class LightProjectile extends Projectile {
    public LightProjectile() throws IOException {
        this.resourcePath = "DankTank"+sep+"src" + sep + "Resources" + sep + "Visual" + sep + "Projectiles" + sep + "Shell_light_strip60.png";
        this.name = "LightProjectile";
        damage = 4;
        velocity = 20;
        this.setBufferedImage();
    }

    public LightProjectile( Tank tank ) throws IOException {
        this();
        this.source = tank;
        this.currentDegrees = tank.getCurrentDegrees();
        x = tank.getXCoordinate() + 20;
        y = tank.getYCoordinate() + 20;
        this.setHitBox();
    }
}
