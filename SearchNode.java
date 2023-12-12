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
    }
    public SearchNode(String name){
        this.name = name;
    }
    public void addChild(SearchNode child){
        this.children.put(child.name, child);
    }

}
