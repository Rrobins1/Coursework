package GameObjects.Projectiles;

import GameObjects.Tanks.Tank;

import java.io.IOException;

public class MediumProjectile extends Projectile {
    public MediumProjectile() {
        this.resourcePath = "DankTank"+sep+"src" + sep + "Resources" + sep + "Visual" + sep + "Projectiles" + sep + "Shell_basic_strip60.png";
        this.name = "MediumProjectile";
        damage = 5;
        velocity = 15;
    }

    public MediumProjectile( Tank tank ) throws IOException {
        this();
        this.source = tank;
        this.currentDegrees = tank.getCurrentDegrees();
        x = tank.getXCoordinate() + 20;
        y = tank.getYCoordinate() + 20;
        this.setHitBox();
    }
}
