import java.util.ArrayList;

public abstract class Space {  
    protected transient Space parent;
    protected int[] xPoints;
    protected int[] yPoints;
    protected String name;
    protected int mapShowCaseRatio = 1;
    protected ArrayList<Space> contents;
   
    public Space(String name){
        this.name = name;
        this.parent = null;
        this.contents = new ArrayList<Space>();
    }
    public String getDirections(){
        Space temp = this;
        ArrayList<String> direction = new ArrayList<String>();
        while(temp!=null){
            direction.add(temp.name);
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
    public ArrayList<Space> getContents(){
        return this.contents;
    }
    public String getName(){
        return this.name;
    }
    public void addParent(Space parent){
        this.parent = parent;
    }
    public boolean equals(Space other){
        if(this.name == other.name){
            return true;
        }
        return false;
    }
    public String toString(){
        String result = "******************************\n";
        result += "Name: " + this.name + " \nContents\n";
        for(int i = 0; i<contents.size();i++){
            result += Integer.toString(i+1) + ": " + contents.get(i).name + " \n"; 
        }
        result += "******************************\n";
        return result;
    }
}
