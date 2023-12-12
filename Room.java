public class Room extends Space{
    public Room(String name) {
        super(name);
    }

    @Override
    public Space contains(Space other) {
        if(this.equals(other)){
            return this;
        }
        return null;
    }
}
