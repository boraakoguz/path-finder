public class Main {
    public static void main(String[] args) {
        Map map = new Map("Bilkent");
        Building Bbuilding = new Building("B Building", map);
        Floor Bfirst = new Floor("First Floor", Bbuilding);
        Floor Bsecond = new Floor("Second Floor", Bbuilding);
        Hallway b1Hallway = new Hallway("Main Hall", Bfirst);
        Hallway b2Hallway = new Hallway("Main Hall", Bsecond);
        Room B1_102 = new Room("B102", b1Hallway);
        Room B1_103 = new Room("B103", b1Hallway);
        Room B1_201 = new Room("B201", b2Hallway);
        System.out.println(B1_102.getDirections());
        System.out.println(B1_103.getDirections());
        System.out.println(b1Hallway.getDirections());
        System.out.println(B1_201.getDirections());
        
    }
}
