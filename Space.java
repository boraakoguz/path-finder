import java.util.ArrayList;

public abstract class Space {
    protected ArrayList<Space> contents;
    protected Space parent;
    protected int[] xPoints;
    protected int[] yPoints;
    protected String name;
    protected int mapShowCaseRatio = 1;

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
