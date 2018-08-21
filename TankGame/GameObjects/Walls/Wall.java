package GameObjects.Walls;

import GameObjects.GameObject;

public abstract class Wall extends GameObject {
    protected static int numberWalls = 0;
    protected int wallID;
    protected int health;

    public static int getNumberWalls() {
        return numberWalls;
    }

    protected void assignID() {
        this.wallID = numberWalls;
        numberWalls++;
    }

    public int getWallID() {
        return this.wallID;
    }
    public void takeDamage( int damage ) {}
    public boolean isDead() {
        return health <= 0;
    }
}
