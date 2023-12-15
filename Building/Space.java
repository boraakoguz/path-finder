package Building;
import java.util.ArrayList;

public abstract class Space {  
    protected transient Space parent;
    protected int[] xPoints;
    protected int[] yPoints;
    protected String name;
    private int mapShowCaseRatio = 1;
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
            temp = temp.parent;
        }
        return path;
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
    public String toString(){
        return this.getName();
    }
}
