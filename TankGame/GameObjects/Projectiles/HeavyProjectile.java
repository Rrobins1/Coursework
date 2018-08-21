package GameObjects.Projectiles;

import GameObjects.Tanks.Tank;

import java.io.IOException;

public class HeavyProjectile extends Projectile {
    public HeavyProjectile() throws IOException {
        this.resourcePath = "DankTank"+sep+"src" + sep + "Resources" + sep + "Visual" + sep + "Projectiles" + sep + "Shell_heavy_strip60.png";
        this.name = "HeavyProjectile";
        damage = 10;
        velocity = 10;
        this.setBufferedImage();
    }

    public HeavyProjectile( Tank tank ) throws IOException {
        this();
        this.source = tank;
        this.currentDegrees = tank.getCurrentDegrees();
        x = tank.getXCoordinate() + 20;
        y = tank.getYCoordinate() + 20;
        this.setHitBox();
    }
}
