package Maps;

import GameObjects.GameObject;
import GameObjects.Walls.Destructable;
import GameObjects.Walls.Indestructable;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class Maps {
    protected static final String sep = System.getProperty( "file.separator" );
    protected static int width;
    protected static int height;
    protected static String[] mapPaths = {
            ("DankTank"+sep+"src" + sep + "Maps" + sep + "level.txt")
    };
    protected static int tank1X, tank1Y, tank2X, tank2Y;
    private static int[][] levelMap;
    public static String getMap( int mapNumber ) {
        return mapPaths[mapNumber];
    }

    public static int[][] parseLevelFile( String resourceLocation ) {
        Path path = FileSystems.getDefault().getPath( resourceLocation );
        try {
            java.util.List<String> levelRows = Files.readAllLines( path );
            height = levelRows.size();
            width = levelRows.get( 0 ).length();

            levelMap = new int[width][height];
            for ( int heightIndex = 0; heightIndex < height; heightIndex++ ) {
                String row = levelRows.get( heightIndex );
                for ( int widthIndex = 0; widthIndex < width; widthIndex++ ) {
                    String currentCharacter = String.valueOf( row.charAt( widthIndex ) );
                    int value;
                    if ( currentCharacter.matches( "\\d+" ) ) {
                        value = Integer.parseInt( currentCharacter );
                    } else {
                        value = 0;
                    }
                    levelMap[widthIndex][heightIndex] = value;
                }
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return levelMap;
    }

    public static HashMap createWallMap( int mapNumber ) throws IOException {
        HashMap<Integer, GameObject> wallMap = new HashMap();
        try {
            parseLevelFile( Maps.getMap( mapNumber ) );
            for ( int heightIndex = 0; heightIndex < height; heightIndex++ ) {
                for ( int widthIndex = 0; widthIndex < width; widthIndex++ ) {
                    if ( levelMap[widthIndex][heightIndex] == 1 ) {
                        Indestructable indestructableWall = new Indestructable( widthIndex * 32, heightIndex * 32 );
                        wallMap.put( indestructableWall.getWallID(), indestructableWall );
                    } else if ( levelMap[widthIndex][heightIndex] == 2 ) {
                        Destructable destructableWall = new Destructable( widthIndex * 32, heightIndex * 32 );
                        wallMap.put( destructableWall.getWallID(), destructableWall );
                    } else if ( levelMap[widthIndex][heightIndex] == 3 ) {
                        tank1X = widthIndex * 32;
                        tank1Y = heightIndex * 32;
                    } else if ( levelMap[widthIndex][heightIndex] == 4 ) {
                        tank2X = widthIndex * 32;
                        tank2Y = heightIndex * 32;
                    }
                }
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return wallMap;
    }

    public static int getTank1XStart() {
        return tank1X;
    }
    public static int getTank1YStart() {
        return tank1Y;
    }
    public static int getTank2XStart() {
        return tank2X;
    }
    public static int getTank2YStart() {
        return tank2Y;
    }
    public static int getWidth() {
        return width;
    }
    public static int getHeight() {
        return height;
    }
}
