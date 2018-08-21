package Movement;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

public class MainMenuInput extends Observable implements KeyListener {
    private int player1Selection = 0;
    private int player2Selection = 0;

    public int getPlayer1Selection() {
        return player1Selection;
    }

    public int getPlayer2Selection() {
        return player2Selection;
    }

    @Override
    public void keyPressed( KeyEvent event ) {
        switch ( event.getKeyCode() ) {
            case KeyEvent.VK_UP:
                player1Selection--;
                break;
            case KeyEvent.VK_DOWN:
                player1Selection++;
                break;
            case KeyEvent.VK_W:
                player2Selection--;
                break;
            case KeyEvent.VK_S:
                player2Selection++;
        }
        player1Selection = player1Selection % 3;
        if ( player1Selection < 0 ) {
            player1Selection = 2;
        }
        player2Selection = player2Selection % 3;
        if ( player2Selection < 0 ) {
            player2Selection = 2;
        }

        setChanged();
        notifyObservers();
    }

    @Override
    public void keyReleased( KeyEvent event ) {
    }

    @Override
    public void keyTyped( KeyEvent event ) {
    }
}
