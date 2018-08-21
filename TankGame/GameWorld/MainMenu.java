package GameWorld;

import GameObjects.Backgrounds.Background;
import GameObjects.Tanks.*;
import Movement.MainMenuInput;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class MainMenu extends JPanel implements Observer {
    Tank heavyBlue, mediumBlue, lightBlue;
    Tank heavyRed, mediumRed, lightRed;
    private Dimension dimension;
    private Background background;
    private MainMenuInput menuInput = new MainMenuInput();
    private Color myRed = new Color( 133, 0, 0 );
    private int width = 600;
    private int height = 650;
    private int player1Offset = 100;
    private int player2Offset = 400;
    private int yOffset = 100;

    public MainMenu() throws IOException {
        this.addKeyListener( menuInput );
        menuInput.addObserver( this );

        this.dimension = new Dimension( width, height );
        this.setPreferredSize( dimension );
        this.setSize( dimension );
        this.setFocusable( true );
        this.requestFocusInWindow();

        background = new Background();

        heavyBlue = new BlueTankHeavy( player1Offset, 150 );
        mediumBlue = new BlueTankMedium( player1Offset, 250 );
        lightBlue = new BlueTankLight( player1Offset, 350 );

        heavyRed = new RedTankHeavy( player2Offset, 150 );
        mediumRed = new RedTankMedium( player2Offset, 250 );
        lightRed = new RedTankLight( player2Offset, 350 );
    }

    @Override
    public void paintComponent( Graphics graphics ) {
        super.paintComponent( graphics );

        for ( int x = 0; x < width; x += background.getWidth() ) {
            for ( int y = 0; y < height; y += background.getHeight() ) {
                background.setXCoordinate( x );
                background.setYCoordinate( y );
                background.repaint( graphics );
            }
        }

        graphics.setFont( new Font( "Copperplate Gothic Light", Font.BOLD, 24 ) );
        graphics.setColor( myRed );
        graphics.drawString( "Player 1", player1Offset, yOffset );

        if ( menuInput.getPlayer1Selection() == 0 ) {
            graphics.setColor( Color.black );
        } else {
            graphics.setColor( myRed );
        }
        graphics.drawString( "Heavy", player1Offset, yOffset + 50 );
        if ( menuInput.getPlayer1Selection() == 1 ) {
            graphics.setColor( Color.black );
        } else {
            graphics.setColor( myRed );
        }
        graphics.drawString( "Medium", player1Offset, yOffset + 150 );
        if ( menuInput.getPlayer1Selection() == 2 ) {
            graphics.setColor( Color.black );
        } else {
            graphics.setColor( myRed );
        }
        graphics.drawString( "Light", player1Offset, 350 );

        graphics.setColor( myRed );
        graphics.drawString( "Player 2", player2Offset, yOffset );
        if ( menuInput.getPlayer2Selection() == 0 ) {
            graphics.setColor( Color.black );
        } else {
            graphics.setColor( myRed );
        }
        graphics.drawString( "Heavy", player2Offset, yOffset + 50 );
        if ( menuInput.getPlayer2Selection() == 1 ) {
            graphics.setColor( Color.black );
        } else {
            graphics.setColor( myRed );
        }
        graphics.drawString( "Medium", player2Offset, yOffset + 150 );
        if ( menuInput.getPlayer2Selection() == 2 ) {
            graphics.setColor( Color.black );
        } else {
            graphics.setColor( myRed );
        }
        graphics.drawString( "Light", player2Offset, yOffset + 250 );

        heavyBlue.repaint( graphics );
        mediumBlue.repaint( graphics );
        lightBlue.repaint( graphics );

        heavyRed.repaint( graphics );
        mediumRed.repaint( graphics );
        lightRed.repaint( graphics );
    }

    @Override
    public void update( Observable observable, Object object ) {
        this.repaint();
    }
}
