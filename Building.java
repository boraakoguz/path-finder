import java.util.ArrayList;

public class Building extends Space{
    public Building(String name,Space parent){
        this.name = name;
        this.parent = parent;
        this.contents = new ArrayList<Space>();
    }
}
