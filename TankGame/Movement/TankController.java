package Movement;

import GameObjects.GameObject;
import GameObjects.Projectiles.Projectile;
import GameObjects.Tanks.Tank;
import GameObjects.Walls.Wall;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class TankController {
    public static void controlTanks( Tank tank, Tank tank2, KeyboardInput keyboardInput, ArrayList<Projectile> projectilesList, HashMap<Integer, Wall> wallMap ) {
        if ( keyboardInput.keyArray[KeyEvent.VK_I] ) {
            tank.forward();
            if ( collisionCheck( tank, tank2, wallMap ) ) tank.placeAtSafeZone();
            else tank.setSafeZone();
        }

        if ( keyboardInput.keyArray[KeyEvent.VK_K] ) {
            tank.backward();
            if ( collisionCheck( tank, tank2, wallMap ) ) tank.placeAtSafeZone();
            else tank.setSafeZone();
        }

        if ( keyboardInput.keyArray[KeyEvent.VK_J] ) {
            tank.rotateLeft();
        }
        if ( keyboardInput.keyArray[KeyEvent.VK_L] ) {
            tank.rotateRight();
        }
        if ( keyboardInput.keyArray[KeyEvent.VK_ENTER] ) {
            try {
                if ( tank.readyToFire() ) projectilesList.add( tank.fire() );
            } catch ( Exception e ) {
            }
        }

        if ( keyboardInput.keyArray[KeyEvent.VK_W] ) {
            tank2.forward();
            if ( collisionCheck( tank2, tank, wallMap ) ) tank2.placeAtSafeZone();
            else tank2.setSafeZone();
        }

        if ( keyboardInput.keyArray[KeyEvent.VK_S] ) {
            tank2.backward();
            if ( collisionCheck( tank2, tank, wallMap ) ) tank2.placeAtSafeZone();
            else tank2.setSafeZone();
        }
        if ( keyboardInput.keyArray[KeyEvent.VK_A] ) {
            tank2.rotateLeft();
        }
        if ( keyboardInput.keyArray[KeyEvent.VK_D] ) {
            tank2.rotateRight();
        }
        if ( keyboardInput.keyArray[KeyEvent.VK_SPACE] ) {
            try {
                if ( tank2.readyToFire() ) projectilesList.add( tank2.fire() );
            } catch ( Exception e ) {
            }
        }
    }

    private static boolean collisionCheck( GameObject gameObject, GameObject gameObject2, HashMap<Integer, Wall> wallMap ) {
        if ( gameObject.collisionOcurred( gameObject2 ) ) return true;
        for ( int i = 0; i < Wall.getNumberWalls(); i++ ) {
            if ( wallMap.containsKey( i ) && gameObject.collisionOcurred( wallMap.get( i ) ) ) return true;
        }
        return false;
    }
}
