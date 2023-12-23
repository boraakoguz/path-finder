package Building;


public class Floor extends Space{
    final public static int GROUND_FLOOR = 0;
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
        String icon = "source/image (23).png";
        if(mapObject.getType() == 0){
            icon = "source/image (16).png";
        }
        mapObject.setIcon(icon);
        this.addSpace(mapObject);
    }
    public void deleteMapObject(MapObject object){
        this.getContents().remove(object);
    }
    public void setDownStairsX(int x){
        this.downStairX = x;
    }
    public void setDownStairsY(int y){
        this.downStairY = y;
    }
    public void setUpStairsX(int x){
        this.upStairX = x;
    }
    public void setUpStairsY(int y){
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

    public String getCustomString() {
        return "Floor" + name;
    }
    
}
