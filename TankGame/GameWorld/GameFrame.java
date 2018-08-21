package GameWorld;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame( JPanel gameWorldPanel ) {
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setResizable( false );
        this.add( gameWorldPanel );
        setVisible( true );
        this.pack();
    }
}
