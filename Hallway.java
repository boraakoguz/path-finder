import java.util.ArrayList;

public class Hallway extends Space{
    public Hallway(String name,Space parent){
        this.name = name;
        this.parent = parent;
        this.contents = new ArrayList<Space>();
    }
}
