package Building;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Mehmet Emre Şahin
 * The object class for the objects that are intractable such as vending machines.
 */
public class MapObject extends Space{

    //Type of the object. currently: 0: Water Dispenser, 1: Vending Machine
    private int type; 
    //A boolean variable indicating whether the object is functional in reailty.
    private boolean functional;
    private String icon;

    //Constructor
    public MapObject(String name, int type, boolean func){
        super(name);
        this.type = type;
        this.functional = func;
    }

    /**
     * Getter for the functionality of the object.
     * @return functionality of the object.
     */
    public boolean getFunctionality() { return this.functional; }

    /**
     * Getter for the type of the object.
     * @return type of the object.
     */
    public int getType() { return this.type; }

    public void setIcon(String icon){
        this.icon = icon;
    }
    public BufferedImage getIcon(){
        try {
            return resize(ImageIO.read(new File(this.icon)),this.getWidth(),this.getHeight());
        } catch (IOException e) {
            return null;
        }
    }
    public BufferedImage getIcon(int w, int h){
        try {
            return resize(ImageIO.read(new File(this.icon)),w,h);
        } catch (IOException e) {
            return null;
        }
    }
    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
    
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
    
        return dimg;
    } 
}
