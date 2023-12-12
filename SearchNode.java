import java.util.HashMap;

public class SearchNode {
    String name;
    HashMap<String,SearchNode> children;
    Space space;
    /*
     * 2 Different constructors for nodes that refer to a space
     * and nodes that do not.
     * ex: BZ-102 refers to a space object 
     *  BZ- does not.
     */
    public SearchNode(String name, Space space){
        this.name = name;
        this.space = space;
        this.children = new HashMap<String,SearchNode>();
    }
    public SearchNode(String name){
        this.name = name;
        this.children = new HashMap<String,SearchNode>();
    }
    public void addChild(SearchNode child){
        this.children.put(child.name, child);
    }
    public HashMap<String,SearchNode> getChildren(){
        return this.children;
    }
    public void addSpace(Space other){
        this.space = other;
    }
    public Space getSpace(){
        return this.space;
    }
    public boolean containsName(String other){
        if (this.name.equals(other)){
            return true;
        }
        return false;
    }
    public String toString(){
        return this.name + " children: " + this.children.toString() +"//";
    }

}
