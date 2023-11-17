import java.util.ArrayList;

public class Floor extends Space{
    public Floor(String name,Space parent){
        this.name = name;
        this.parent = parent;
        this.contents = new ArrayList<Space>();
    }
}
