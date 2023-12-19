package Building;
import java.awt.Color;
import java.util.ArrayList;

public abstract class Space {  
    protected transient Space parent;
    protected int xPoint;
    protected int yPoint;
    protected int width;
    protected int height;
    protected int entranceX;
    protected int entranceY;
    protected int colorR;
    protected int colorG;
    protected int colorB;
    protected String name;
    protected ArrayList<Space> contents;
    transient private boolean isHighlighted = false;
   
    public Space(String name){
        this.name = name;
        this.parent = null;
        this.contents = new ArrayList<Space>();
    }
    public ArrayList<Space> getDirections(){
        Space temp = this;
        ArrayList<Space> path = new ArrayList<Space>();
        while(temp!=null){
            path.add(temp);
            if(temp instanceof Floor && ((Floor)temp).getFloorNumber() != 0){
                temp = getNextFloor((Floor)temp);
            }
            else{
                temp = temp.parent;
            }
        }
        return path;
    }
    public Floor getNextFloor(Floor floor){
        int currentFloorNum = floor.getFloorNumber();
        int target = 1;
        if(currentFloorNum<0){
            target = -1;
        }
        Space building = floor.getParent();
        for (Space space : building.getContents()) {
            Floor nextFloor = (Floor) space;
            if(currentFloorNum - nextFloor.getFloorNumber()==target){
                return nextFloor;
            }
        }
        return null;
    }
    public String getDirectionsAsString(){
        Space temp = this;
        ArrayList<String> direction = new ArrayList<String>();
        while(temp!=null){
            direction.add(temp.getName());
            temp = temp.parent;
        }
        String result = "";
        for (int i = direction.size()-1;i>-1;i--) {
            if(i == direction.size()-1){
                result += direction.get(i);
            }
            else{
                result += " -> " + direction.get(i);
            }
        }
        return result;

    }
    public Space contains(Space other){
        for (Space space : contents) {
            if(space.equals(other)){
                return space;
            }
        }
        return null;
    }
    
    public void addSpace(Space other){
        this.contents.add(other);
        other.parent = this;
    }
    public boolean getIsHıghlighted(){
        return this.isHighlighted;
    }
    public void setIsHighlighted(boolean newValue){
        this.isHighlighted = newValue;
    }
    public ArrayList<Space> getContents(){
        return this.contents;
    }
    public String getName(){
        return this.name;
    }
    public void addParent(Space parent){
        this.parent = parent;
    }
    public Space getParent(){
        return this.parent;
    }
    public boolean hasParent(){
        return this.parent != null;
    }
    public boolean equals(Space other){
        if(this.name == other.name){
            return true;
        }
        return false;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setX(int x){
        this.xPoint = x;
    }
    public void setY(int y){
        this.yPoint = y;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void setEntranceX(int x){
        this.entranceX = x;
    }
    public void setEntranceY(int y){
        this.entranceY = y;
    }
    public void setColor(Color color){
        this.colorR = color.getRed();
        this.colorG = color.getGreen();
        this.colorB = color.getBlue();
    }
    public int getX(){
        return this.xPoint;
    }
    public int getY(){
        return this.yPoint;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    public int getEntranceX(){
        return this.entranceX;
    }
    public Color getColor(){
        return new Color(this.colorR,this.colorG,this.colorB);
    }
    public int getEntranceY(){
        return this.entranceY;
    }
    public String toString(){
        return this.getName();
    }
}
