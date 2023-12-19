package Building;


public class Floor extends Space{
    protected int floorNumber;
    protected int downStairX;
    protected int downStairY;
    protected int upStairX;
    protected int upStairY;
    public Floor(String name,int floorNumber) {
        super(name);
        this.floorNumber = floorNumber;
    }
    public void addMapObject(MapObject mapObject){
        String icon = "image (23).png";
        if(mapObject.getType() == 0){
            icon = "image (16).png";
        }
        mapObject.setIcon(icon);
        this.addSpace(mapObject);
    }
    public void deleteMapObject(MapObject object){
        this.getContents().remove(object);
    }
    public void setDownStairX(int x){
        this.downStairX = x;
    }
    public void setDownStairY(int y){
        this.downStairY = y;
    }
    public void setUpStairX(int x){
        this.upStairX = x;
    }
    public void setUpStairY(int y){
        this.upStairY = y;
    }
    public void setFloorNumber(int newNumber){
        this.floorNumber = newNumber;
    }
    public int getFloorNumber(){
        return this.floorNumber;
    }
    public int getDownStairX(){
        return this.downStairX;
    }
    public int getDownStairY(){
        return this.downStairY;
    }
    public int getUpStairX(){
        return this.upStairX;
    }
    public int getUpStairY(){
        return this.upStairY;
    }
    
}
