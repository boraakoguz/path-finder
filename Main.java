public class Main {
    public static void main(String[] args) {
        Map map = new Map("Bilkent");
        Building Bbuilding = new Building("B Building");
        Floor Bfirst = new Floor("First Floor");
        Floor Bsecond = new Floor("Second Floor");
        Hallway b1Hallway = new Hallway("Main Hall");
        Hallway b2Hallway = new Hallway("Main Hall");
        Room B1_102 = new Room("B102");
        Room B1_103 = new Room("B103");
        Room B1_201 = new Room("B201");
        map.addSpace(Bbuilding);
        Bbuilding.addSpace(Bfirst);
        Bbuilding.addSpace(Bsecond);
        Bfirst.addSpace(B1_102);
        System.out.println(B1_102.getDirections());
        System.out.println(B1_103.getDirections());
        System.out.println(b1Hallway.getDirections());
        System.out.println(B1_201.getDirections());
        LoadSave loadSave = new LoadSave("a");
        loadSave.save(map);
        System.out.println(map);
        Map loaded = loadSave.load();
        System.out.println(loaded);
        System.out.println(loaded.contents.get(0).contents);
        
        
    }
}
