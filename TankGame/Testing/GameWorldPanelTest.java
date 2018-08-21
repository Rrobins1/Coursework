package Testing;

import GameWorld.GameFrame;
import GameWorld.GameWorldPanel;

import java.io.IOException;

public class GameWorldPanelTest {
    public static void main( String[] args ) throws IOException {
        new GameFrame( new GameWorldPanel() );
    }
}
