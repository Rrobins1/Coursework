package Movement;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

public class KeyboardInput extends Observable implements KeyListener {
    public boolean[] keyArray = new boolean[1000];

    @Override
    public void keyPressed( KeyEvent event ) {
        keyArray[event.getKeyCode()] = true;
        setChanged();
        notifyObservers();
    }

    @Override
    public void keyReleased( KeyEvent event ) {
        keyArray[event.getKeyCode()] = false;
        setChanged();
        notifyObservers();
    }

    @Override
    public void keyTyped( KeyEvent event ) {
    }
}
