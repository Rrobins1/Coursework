package GameWorld;

import Clock.GameClock;
import GameObjects.Backgrounds.Background;
import GameObjects.GameObject;
import GameObjects.Projectiles.Projectile;
import GameObjects.Tanks.BlueTankHeavy;
import GameObjects.Tanks.RedTankLight;
import GameObjects.Tanks.Tank;
import GameObjects.Walls.Wall;
import Graphics.Explosions.Explosion;
import Maps.Maps;
import Movement.KeyboardInput;
import Movement.ProjectileController;
import Movement.TankController;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public final class GameWorldPanel extends JPanel implements Observer {
    private static final int MAPNUMBER = 0;
    private static final int PLAYERIMAGEWIDTH = 550;
    private static final int PLAYERIMAGEHEIGHT = 550;
    private static final int PLAYERIMAGEGAP = 150;
    private static HashMap<Integer, Wall> wallMap;
    private BufferedImage gameImage, player1Image, player2Image;
    private Image miniMap;
    private ArrayList<Projectile> projectilesList = new ArrayList<Projectile>();
    private ArrayList<Explosion> explosionList = new ArrayList<Explosion>();
    private int height, width;
    private Dimension dimension;

    private int player1Lives = 2;
    private int player2Lives = 2;

    private Tank tank;
    private Tank tank2;
    private GameObject background;
    private KeyboardInput keyboardInput = new KeyboardInput();
    private GameClock gameClock = new GameClock();

    public GameWorldPanel() throws IOException {
        wallMap = Maps.createWallMap( MAPNUMBER );
        width = 32 * Maps.getWidth();
        height = 32 * Maps.getHeight();
        gameImage = new BufferedImage( 32 * Maps.getWidth(), 32 * Maps.getHeight(), BufferedImage.TYPE_INT_RGB );

        this.dimension = new Dimension( PLAYERIMAGEWIDTH * 2 + PLAYERIMAGEGAP, PLAYERIMAGEHEIGHT );
        this.setPreferredSize( dimension );
        this.setSize( dimension );
        this.setFocusable( true );
        this.requestFocusInWindow();

        keyboardInput.addObserver( this );
        this.addKeyListener( keyboardInput );

        gameClock.addObserver( this );
        new Thread( this.gameClock ).start();

        background = new Background();
        tank = new BlueTankHeavy( Maps.getTank1XStart(), Maps.getTank1YStart() );
        tank2 = new RedTankLight( Maps.getTank2XStart(), Maps.getTank2YStart() );
    }

    @Override
    public void update( Observable observable, Object object ) {
        TankController.controlTanks( tank, tank2, keyboardInput, projectilesList, wallMap );
        try {
            ProjectileController.manageProjectiles( tank, tank2, keyboardInput, projectilesList, wallMap, explosionList );
        } catch ( Exception e ) {
        }
        try {
            if ( tank.isDead() ) {
                player1Lives = player1Lives - 1;
                tank = new BlueTankHeavy( Maps.getTank1XStart(), Maps.getTank1YStart() );
            }
            if ( tank2.isDead() ) {
                player2Lives = player2Lives - 1;
                tank2 = new RedTankLight( Maps.getTank2XStart(), Maps.getTank2YStart() );
            }
        } catch ( Exception e ) {
        }
        this.repaint();
    }

    private BufferedImage setPlayerImage( Tank tank ) {
        int xOffset, yOffset;

        xOffset = ( int ) tank.getXCoordinate() - PLAYERIMAGEWIDTH / 2;
        if ( xOffset < 0 ) {
            xOffset = 0;
        }
        if ( (xOffset + PLAYERIMAGEWIDTH) >= width ) {
            xOffset = width - PLAYERIMAGEWIDTH;
        }

        yOffset = ( int ) tank.getYCoordinate() - PLAYERIMAGEHEIGHT / 2;
        if ( yOffset < 0 ) {
            yOffset = 0;
        }
        if ( (yOffset + PLAYERIMAGEHEIGHT) >= height ) {
            yOffset = height - PLAYERIMAGEHEIGHT;
        }

        return gameImage.getSubimage( xOffset, yOffset, PLAYERIMAGEWIDTH, PLAYERIMAGEHEIGHT );
    }

    @Override
    public void paintComponent( Graphics graphics ) {
        Graphics2D graphics2d = ( Graphics2D ) graphics;
        graphics2d = gameImage.createGraphics();

        super.paintComponent( graphics2d );
        paintEnvironment( graphics2d );
        tank.repaint( graphics2d );
        tank2.repaint( graphics2d );
        paintExplosions( graphics2d );
        graphics.drawImage( gameImage, 0, 0, this );

        player1Image = setPlayerImage( tank );
        player2Image = setPlayerImage( tank2 );
        miniMap = gameImage.getScaledInstance( PLAYERIMAGEGAP, PLAYERIMAGEGAP, 0 );

        graphics.drawImage( player1Image, 0, 0, PLAYERIMAGEWIDTH, PLAYERIMAGEHEIGHT, null );
        graphics.drawImage( player2Image, PLAYERIMAGEWIDTH + PLAYERIMAGEGAP, 0, PLAYERIMAGEWIDTH, PLAYERIMAGEHEIGHT, null );
        graphics.fillRect( PLAYERIMAGEWIDTH, 0, PLAYERIMAGEGAP, PLAYERIMAGEHEIGHT );
        graphics.drawImage( miniMap, PLAYERIMAGEWIDTH, PLAYERIMAGEHEIGHT / 2 - PLAYERIMAGEGAP / 2, this );
        drawHealthBars( graphics );
        drawControls( graphics );

        if ( (player1Lives == 0) || (player2Lives == 0) ) {
            gameOver( graphics );
        }
    }

    private void drawControls( Graphics graphics ) {
        graphics.setFont( new Font( "Copperplate Gothic Light", Font.BOLD, 16 ) );
        graphics.setColor( Color.GRAY );

        graphics.drawString( "PLAYER 1", PLAYERIMAGEWIDTH + 10, 30 );
        graphics.drawString( "I:  Forward", PLAYERIMAGEWIDTH + 10, 50 );
        graphics.drawString( "K:  Backward", PLAYERIMAGEWIDTH + 10, 70 );
        graphics.drawString( "J:  Left", PLAYERIMAGEWIDTH + 10, 90 );
        graphics.drawString( "L:  Right", PLAYERIMAGEWIDTH + 10, 110 );
        graphics.drawString( "Enter:  Fire", PLAYERIMAGEWIDTH + 10, 130 );

        graphics.drawString( "PLAYER 2", PLAYERIMAGEWIDTH + 10, 380 );
        graphics.drawString( "W:  Forward", PLAYERIMAGEWIDTH + 10, 400 );
        graphics.drawString( "S:  Backward", PLAYERIMAGEWIDTH + 10, 420 );
        graphics.drawString( "A:  Left", PLAYERIMAGEWIDTH + 10, 440 );
        graphics.drawString( "D:  Right", PLAYERIMAGEWIDTH + 10, 460 );
        graphics.drawString( "Space:  Fire", PLAYERIMAGEWIDTH + 10, 480 );
    }

    private void drawHealthBars( Graphics graphics ) {
        graphics.setColor( Color.GREEN );

        graphics.fillRect( 0, 0, tank.getHealth(), 30 );
        graphics.fillRect( PLAYERIMAGEWIDTH + PLAYERIMAGEGAP, 0, tank2.getHealth(), 30 );

        graphics.setColor( Color.BLACK );
        graphics.drawRect( 0, 0, tank.getHealth(), 30 );
        graphics.drawString( "" + tank.getHealth(), 0, 25 );

        graphics.drawRect( PLAYERIMAGEWIDTH + PLAYERIMAGEGAP, 0, tank2.getHealth(), 30 );
        graphics.drawString( "" + tank2.getHealth(), PLAYERIMAGEWIDTH + PLAYERIMAGEGAP, 25 );

        for ( int index = 0; index < player1Lives; index++ ) {
            graphics.fillOval( 10 * index, 30, 10, 10 );
        }
        for ( int index = 0; index < player2Lives; index++ ) {
            graphics.fillOval( PLAYERIMAGEWIDTH + PLAYERIMAGEGAP + 10 * index, 30, 10, 10 );
        }
    }

    private void paintEnvironment( Graphics graphics ) {
        for ( int x = 0; x < width; x += background.getWidth() ) {
            for ( int y = 0; y < height; y += background.getHeight() ) {
                background.setXCoordinate( x );
                background.setYCoordinate( y );
                background.repaint( graphics );
            }
        }
        for ( int i = 0; i < Wall.getNumberWalls(); i++ ) {
            if ( wallMap.containsKey( i ) ) wallMap.get( i ).repaint( graphics );
        }
        for ( int i = 0; i < projectilesList.size(); i++ ) {
            projectilesList.get( i ).repaint( graphics );
        }
    }

    private void paintExplosions( Graphics graphics ) {
        for ( int i = 0; i < explosionList.size(); i++ ) {
            explosionList.get( i ).repaint( graphics );
            if ( explosionList.get( i ).isStopped() ) explosionList.remove( i );
        }

    }

    private void gameOver( Graphics graphics ) {
        graphics.setFont( new Font( "Copperplate Gothic Light", Font.BOLD, 40 ) );

        graphics.setColor( Color.BLACK );
        graphics.fillRect( 0, 0, PLAYERIMAGEGAP + 2 * PLAYERIMAGEWIDTH, PLAYERIMAGEHEIGHT );

        graphics.setColor( Color.RED );
        if ( player1Lives == 0 ) {
            graphics.drawString( "PLAYER 2 IS VICTORIOUS", PLAYERIMAGEWIDTH - 200, PLAYERIMAGEHEIGHT / 2 - 100 );
        } else {
            graphics.drawString( "PLAYER 1 IS VICTORIOUS", PLAYERIMAGEWIDTH - 200, PLAYERIMAGEHEIGHT / 2 - 100 );
        }

        gameClock.deleteObservers();
        this.removeKeyListener( keyboardInput );
    }
}

