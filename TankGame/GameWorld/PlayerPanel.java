//package GameWorld;
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import javax.swing.*;
//import javax.imageio.ImageIO;
//import GameObjects.Tanks.Tank;
//import java.awt.Graphics.*;
//import java.awt.Graphics2D;
//import java.awt.Dimension;
//import java.awt.Image;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowListener;
//import java.awt.Toolkit;
//import java.io.File;
//import java.io.IOException;
//
//
///**
// * Created by lorraine on 4/28/17.
// */
//public class PlayerPanel extends JPanel{
//    private  int imageX,imageY;
//
//    private static Dimension dimension;
//    private static Dimension MapDimension;
//
//    BufferedImage image, PlayerPanel;
//
//
//    //Graphics2D graphics2d = (Graphics2D) graphics;
//
//    //graphics2d.toImage();
//
//public PlayerPanel(Tank tank, BufferedImage image){
//
//
//
//    imageX =(int)tank.getXCoordinate()-image.getWidth()/2;
//    imageY =(int)tank.getYCoordinate()-image.getHeight()/2;
//
//
//
//
//    PlayerPanel =image.getSubimage(imageX,imageY, image.getWidth()/2, image.getHeight()/2);
//
//
//
//
//
//}
//
//public getImage(){
//    return image;
//}
//
//
//
//
//public void drawimage(BufferedImage image){
//    this.image = subimsge
//
//}
//
//public void paintComponent (Graphics graphics) {
//    Graphics2D graphics2d = (Graphics2D) graphics;
//
//    image = graphics2d.createGraphics();
//    graphics2d = image.createGraphics();
//    //PlayerPanel = image.getSubimage(imageX, imageY, image.getWidth() / 2, image.getHeight() / 2);
//   // graphics2d.drawImage(PlayerPanel, 0, 0, this);
//    try {
//        ImageIO.write(image, "PNG", new File("image.png"));
//        BufferedImage imageread = ImageIO.read(new File("image.png"));
//    } catch (IOException e) {
//
//
//    }
//}
//    public void setImage(BufferedImage image){
//        image = new BufferedImage(getWidth(), getHeight(),BufferedImage.TYPE_3BYTE_BGR);
//    }
//
//}
