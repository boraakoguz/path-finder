package Building;


public class Building extends Space{
    public int maxFloor = 0;
    public int minFloor = 0;
    public int numberOfFloors = 1;
    public Building(String name) {
        super(name);
        this.contents.add(new Floor("Ground Floor",0));
        this.maxFloor = 1;
        this.numberOfFloors = 1;
        this.minFloor = -1;
    }
    public void addFloor(String floorName, boolean addToTop){
        if(addToTop){
            Floor floor = new Floor(floorName, maxFloor);
            this.maxFloor++;
            this.addSpace(floor);
        }
        else{
            Floor floor = new Floor(floorName, minFloor);
            this.minFloor--;
            this.addSpace(floor);
        }
    }
    public boolean deleteFloor(Floor floor){
        if(floor.getFloorNumber() != maxFloor-1 && floor.getFloorNumber() != minFloor+1){
            return false;
        }
        if(floor.getFloorNumber() == maxFloor){
            this.contents.remove(floor);
            maxFloor--;
        }
        else{
            this.contents.remove(floor);
            minFloor++;
        }
        return true;
    }
}
