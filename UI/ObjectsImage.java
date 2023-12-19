package UI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class ObjectsImage extends JFrame{
    Image image;
    BufferedImage buffered;
    BufferedImage buffered2;
    //public BufferedImage buffImg = new BufferedImage(240, 240, BufferedImage.TYPE_INT_ARGB);
    public ObjectsImage() throws IOException {
         
        
        image = ImageIO.read(new File("image (23).png"));
        buffered = (BufferedImage) image;
        buffered2 = resize((BufferedImage) image, 20, 20);
        setSize(400, 400);
        setVisible(true);
        repaint();
    }
    @Override
    public void paint(Graphics g) {
        g.drawImage(buffered2, 0, 0, null);
        g.drawRect(10, 10, 10, 10);
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
    
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
    
        return dimg;
    }  
    public static void main(String[] args) throws IOException {
        new ObjectsImage();
    }
}
