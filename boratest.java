import java.util.ArrayList;

import Building.Building;
import Building.Floor;
import Building.Hallway;
import Building.Map;
import Building.MapObject;
import Building.Room;
import Building.Search;
import Building.Space;
import UI.Controller;
import Utilities.Feedback;
import Utilities.FeedbackContainer;
import Utilities.LoadSave;

public class boratest {
    public static void main(String[] args) {
        Feedback f1 = new Feedback("xyz çalışmıyor",true);
        Feedback f2 = new Feedback("abc çalışmıyor",true);
        Feedback f3 = new Feedback("adsdad çalışmıyor",true);
        Feedback f4 = new Feedback("lan çalışmıyor",true);
        ArrayList<Feedback> fbList = new ArrayList<Feedback>();
        fbList.add(f1);
        fbList.add(f2);
        fbList.add(f3);
        fbList.add(f4);
        FeedbackContainer container = new FeedbackContainer(fbList);

        Map map = new Map("Bilkent");
        Building Bbuilding = new Building("B Building");
        Floor Bfirst = new Floor("First Floor");
        Floor Bsecond = new Floor("Second Floor");
        Hallway b1Hallway = new Hallway("Main Hall");
        Hallway b2Hallway = new Hallway("Main Hall");
        Room B1_102 = new Room("B102");
        Room B1_103 = new Room("B103");
        Room B1_201 = new Room("B201");
        MapObject vending = new MapObject("B First Floor Vending Machine",0,true);
        map.addSpace(Bbuilding);
        map.addFeedBackContainer(container);
        Bbuilding.addSpace(Bfirst);
        Bbuilding.addSpace(Bsecond);
        Bfirst.addSpace(b1Hallway);
        Bfirst.addSpace(b2Hallway);
        b1Hallway.addSpace(B1_102);
        b1Hallway.addSpace(vending);
        b2Hallway.addSpace(B1_103);
        Bsecond.addSpace(B1_201);
        LoadSave loadSave = new LoadSave();
        loadSave.save(map);
        ArrayList<Map> loadedMaps = loadSave.loadSaves();
        Map loaded = loadedMaps.get(0);
        //System.out.println(loaded.getName());
        //System.out.println("Loaded map data \n" + loaded.contents.get(0).contents.get(0).getDirections());
        Search search = new Search();
        search.initializeSearchTree(loaded);
        Controller controller = new Controller();
        controller.setCurrentMap("Bilkent");
        ArrayList<Space> start = controller.search("B102");
        ArrayList<Space> destination = controller.search("B201");
        ArrayList<Space> a = controller.search("B");
        System.out.println("///////////////////////////////////////////////////////////");
        System.out.println(controller.getDirections(start.get(0),destination.get(0)));
        System.out.println(a);
        System.out.println("///////////////////////////////////////////////////////////");
        ArrayList<Space> found = controller.nearestMapObjects(a.get(1),0);
        System.out.println(found);
        //System.out.println(search.search("B102").getDirections());
        //System.out.println("\nSearch result: " + search.search("B"));
        System.out.println(map.getFeedbackContainer().getFeedBackList().toString());
        System.out.println(loadedMaps.get(0).getFeedbackContainer().getFeedBackList().get(0));
        

    }
}
