package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ObjectsImage extends JPanel{
    BufferedImage buffered;
    BufferedImage buffered2;
    //public BufferedImage buffImg = new BufferedImage(240, 240, BufferedImage.TYPE_INT_ARGB);
    public ObjectsImage() throws IOException {
         
        setBackground(Color.BLACK);
        buffered = resize((BufferedImage) ImageIO.read(new File("image (23).png")), 5000, 5000);
        buffered2 = (BufferedImage) ImageIO.read(new File("image (23).png"));
        setSize(400, 400);
        setVisible(true);
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(buffered2, 0, 0, null);
        
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
        ObjectsImage test = new ObjectsImage();
        JFrame frame = new JFrame();
        frame.add(test);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
