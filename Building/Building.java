package Building;

import java.awt.Color;

public class Building extends Space{
    public int maxFloor = 0;
    public int minFloor = 0;
    public int numberOfFloors = 0;
    public final static int FLOOR_GROUND = 0;
    public final static int FLOOR_UP = 1;
    public final static int FLOOR_DOWN = 2;
    public Building(String name) {
        super(name);
    }
    public void addFloor(int floorType){
        Floor floor;
        if(floorType == FLOOR_GROUND){
            floor = new Floor("Ground Floor", maxFloor);
            this.maxFloor++;
            this.minFloor--;
            this.addSpace(floor);
        }
        else if(floorType == FLOOR_UP){
            floor = new Floor(""+ maxFloor, maxFloor);
            this.maxFloor++;
            this.addSpace(floor);
            System.out.println("add up");
        }
        else {
            floor = new Floor(""+minFloor, minFloor);
            this.minFloor--;
            this.addSpace(floor);   
            System.out.println("add down"); 
        }
        floor.setX(getX());
        floor.setY(getY());
        floor.setWidth(getWidth());
        floor.setHeight(getHeight());
        floor.setColor(Color.BLACK);
        floor.setEntranceX(getEntranceX());
        floor.setEntranceY(getEntranceY());
        floor.setUpStairsX(getX() + 10);
        floor.setUpStairsY(getY() +getHeight()/2);
        floor.setDownStairsX(getWidth() + getX() - 10);
        floor.setDownStairsY(getY() +getHeight()/2);
        numberOfFloors++;
    }

    public boolean deleteFloor(Floor floor){
        System.out.println("Floor number: " + floor.getFloorNumber());
        System.out.println(minFloor);
        System.out.println(maxFloor);
        if(floor.getFloorNumber() != maxFloor-1 && floor.getFloorNumber() != minFloor+1){
            System.out.println("İnside 1");
            return false;
            
        }
        if(floor.getFloorNumber() == FLOOR_GROUND && numberOfFloors == 1) {
            this.getParent().getContents().remove(this);
            System.out.println("İnside 2");
            return true;
        }
        if(floor.getFloorNumber() == maxFloor-1){
            System.out.println("İnside 3");
            this.contents.remove(floor);
            maxFloor--;
            numberOfFloors--;
        }
        else if(floor.getFloorNumber() == minFloor+1){
            System.out.println("İnside 4");
            this.contents.remove(floor);
            minFloor++;
            numberOfFloors--;
        }
        return true;
    }

    public boolean floorCanBeDeleted(Floor floor) {
        if(floor.getFloorNumber() != maxFloor-1 && floor.getFloorNumber() != minFloor+1){
            return false;
        }
        return true;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public int getMinFloor() {
        return minFloor;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        for (Space space : getContents()) {
            space.setColor(color);
        }
    }

    
    public String getCustomString() {
        return "Building " + name;
    }
}
