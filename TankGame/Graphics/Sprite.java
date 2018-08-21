package Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {
    private int numberXFrames, numberYFrames, Xstep, Ystep;
    private String spriteFilePath;
    private BufferedImage[] images;

    public Sprite( String spriteFilePath, int numberXFrames, int numberYFrames ) throws IOException {
        this.spriteFilePath = spriteFilePath;
        this.numberXFrames = numberXFrames;
        this.numberYFrames = numberYFrames;
        this.loadImages();
    }

    private void loadImages() throws IOException {
        BufferedImage image = ImageIO.read( new File( spriteFilePath ) );
        this.Xstep = image.getWidth() / numberXFrames;
        this.Ystep = image.getHeight() / numberYFrames;
        this.images = new BufferedImage[numberXFrames + numberYFrames];

        for ( int yIndex = 0; yIndex < numberYFrames; yIndex++ ) {
            for ( int xIndex = 0; xIndex < numberXFrames; xIndex++ ) {
                this.images[yIndex + xIndex] = image.getSubimage( xIndex * Xstep, yIndex * Ystep, Xstep, Ystep );
            }
        }
    }

    public BufferedImage getFrame( int frame ) {
        return this.images[frame];
    }
    public int frameCount() {
        return images.length;
    }
    public int getYstep() {
        return Ystep;
    }
    public int getXstep() {
        return Xstep;
    }
}
