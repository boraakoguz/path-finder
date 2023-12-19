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
        }
        else {
            floor = new Floor(""+minFloor, minFloor);
            this.minFloor--;
            this.addSpace(floor);    
        }
        floor.setX(getX());
        floor.setY(getY());
        floor.setWidth(getWidth());
        floor.setHeight(getHeight());
        floor.setColor(Color.BLACK);
        floor.setEntranceX(getEntranceX());
        floor.setEntranceY(getEntranceY());
<<<<<<< HEAD
        floor.setUpStairsX(getX() + 10);
        floor.setUpStairsY(getY() +getHeight()/2);
        floor.setDownStairsX(getWidth() + getX() - 10);
        floor.setDownStairsY(getY() +getHeight()/2);
        floor.setName("Ground Floor");
        System.out.println(floor.getDownStairX());
        System.out.println(floor.getDownStairY());
        System.out.println(floor.getUpStairX());
        System.out.println(floor.getUpStairY ());
        System.out.println("aasasdjlsasddsfsdlkfjsdflkhjlfkjsdflkjsdlfçjsdlfkçsdjflsdkçfjsdlkfçjsdlfkm");
=======
        floor.setUpStairX(getX() + 10);
        floor.setUpStairY(getY() +getHeight()/2);
        floor.setDownStairX(getWidth() + getX() - 10);
        floor.setDownStairY(getY() +getHeight()/2);
>>>>>>> c09034625828ede33fa8a0c6f32952ed014702de
        numberOfFloors++;
    }
    public boolean deleteFloor(Floor floor){
        if(floor.getFloorNumber() != maxFloor-1 && floor.getFloorNumber() != minFloor+1){
            return false;
        }
        if(floor.getFloorNumber() == 0 && numberOfFloors == 1){
            this.contents.remove(floor);
            maxFloor--;
            numberOfFloors--;
            this.getParent().getContents().remove(this);
        }
        if(floor.getFloorNumber() == maxFloor){
            this.contents.remove(floor);
            maxFloor--;
            numberOfFloors--;
        }
        else{
            this.contents.remove(floor);
            minFloor++;
            numberOfFloors--;
        }
        return true;
    }
}
