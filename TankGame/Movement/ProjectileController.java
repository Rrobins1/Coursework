package Movement;

import GameObjects.Projectiles.Projectile;
import GameObjects.Tanks.Tank;
import GameObjects.Walls.Wall;
import Graphics.Explosions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjectileController {

    public static void manageProjectiles( Tank tank, Tank tank2, KeyboardInput keyboardInput, ArrayList<Projectile> projectileList, HashMap<Integer, Wall> wallMap, ArrayList<Explosion> explosionList ) throws IOException {
        Projectile currentProjectile;
        for ( int i = 0; i < projectileList.size(); i++ ) {
            currentProjectile = projectileList.get( i );
            currentProjectile.forward();
            if ( projectileCollisionCheck( currentProjectile, tank, tank2, wallMap ) ) {
                switch ( currentProjectile.getName() ) {
                    case "HeavyProjectile":
                        explosionList.add( new LargeExplosion( ( int ) currentProjectile.getXCoordinate(), ( int ) currentProjectile.getYCoordinate() ) );
                        break;
                    case "MediumProjectile":
                        explosionList.add( new MediumExplosion( ( int ) currentProjectile.getXCoordinate(), ( int ) currentProjectile.getYCoordinate() ) );
                        break;
                    case "LightProjectile":
                        explosionList.add( new SmallExplosion( ( int ) currentProjectile.getXCoordinate(), ( int ) currentProjectile.getYCoordinate() ) );
                        break;
                }
                projectileList.remove( i );
            }
        }
        if ( tank.isDead() ) {
            explosionList.add( new TankExplosion( ( int ) tank.getXCoordinate(), ( int ) tank.getYCoordinate() ) );
        }
        if ( tank2.isDead() ) {
            explosionList.add( new TankExplosion( ( int ) tank2.getXCoordinate(), ( int ) tank2.getYCoordinate() ) );
        }
    }

    private static boolean projectileCollisionCheck( Projectile projectile, Tank tank, Tank tank2, HashMap<Integer, Wall> wallMap ) {
        if ( projectile.getSource() != tank ) {
            if ( projectile.collisionOcurred( tank ) ) {
                tank.takeDamage( projectile.getDamage() );
                return true;
            }
        }

        if ( projectile.getSource() != tank2 ) {
            if ( projectile.collisionOcurred( tank2 ) ) {
                tank2.takeDamage( projectile.getDamage() );
                return true;
            }
        }

        for ( int index = 0; index < Wall.getNumberWalls(); index++ ) {
            if ( wallMap.containsKey( index ) && projectile.collisionOcurred( wallMap.get( index ) ) ) {
                wallMap.get( index ).takeDamage( projectile.getDamage() );
                if ( wallMap.get( index ).isDead() ) wallMap.remove( index );
                return true;
            }
        }
        return false;
    }
}
