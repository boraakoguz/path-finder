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
        LoadSave loadSave = new LoadSave("save.json");
        loadSave.save(map);
        Map loaded = loadSave.load();
        System.out.println("Loaded map data \n" + loaded.contents.get(0).contents.get(0).getDirections());
        Search search = new Search(loaded);
        System.out.println(search.search("B102").getDirections());
        System.out.println(search.search("B"));
    }
}
