package Testing;

import GameWorld.GameFrame;
import GameWorld.MainMenu;

import java.io.IOException;

public class MenuTest {
    public static void main( String[] args ) throws IOException {
        new GameFrame( new MainMenu() );
    }
}
