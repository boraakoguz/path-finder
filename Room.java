public class Room extends Space{
    public Room(String name,Space parent){
        this.name = name;
        this.parent = parent;
    }

    @Override
    public Space contains(Space other) {
        if(this.equals(other)){
            return this;
        }
        return null;
    }
}
