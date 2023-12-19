package Building;

import java.awt.image.BufferedImage;

/**
 * @author Mehmet Emre Şahin
 * The object class for the objects that are intractable such as vending machines.
 */
public class MapObject extends Space{

    //Type of the object. currently: 0: Water Dispenser, 1: Vending Machine
    private int type; 
    //A boolean variable indicating whether the object is functional in reailty.
    private boolean functional;
    private BufferedImage icon;

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
    
    public void setIcon(BufferedImage icon){
        this.icon = icon;
    }
    public BufferedImage getIcon(){
        return this.icon;
    }
}
