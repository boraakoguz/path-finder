package Building;

public class Floor extends Space{
    int floorNumber;
    public Floor(String name,int floorNumber) {
        super(name);
        this.floorNumber = floorNumber;
    }
    public void setFloorNumber(int newNumber){
        this.floorNumber = newNumber;
    }
    public int getFloorNumber(){
        return this.floorNumber;
    }
    
}
