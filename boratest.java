import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import Building.Building;
import Building.Floor;
import Building.Map;
import Building.MapObject;
import Building.Room;
import Building.Search;
import Building.Space;
import UI.Controller;
import Utilities.Feedback;
import Utilities.FeedbackContainer;
import Utilities.LoadSave;
import Utilities.Login;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;

public class boratest {

    public static void main(String[] args) throws FirebaseException, UnsupportedEncodingException, JacksonUtilityException {
        
        Feedback f1 = new Feedback("q@hotmail","Bora","xyz çalışmıyor","Bilkent","B Building","First Floor","B102",true);
        Feedback f2 = new Feedback("q@hotmail","Bora","xyz çalışmıyor","Bilkent","B Building","First Floor","B102",true);
        Feedback f3 = new Feedback("q@hotmail","Bora","xyz çalışmıyor","Bilkent","B Building","First Floor","B102",true);
        Feedback f4 = new Feedback("q@hotmail","Bora","xyz çalışmıyor","Bilkent","B Building","First Floor","B102",true);
        
        ArrayList<Feedback> fbList = new ArrayList<Feedback>();
        fbList.add(f1);
        fbList.add(f2);
        fbList.add(f3);
        fbList.add(f4);
        FeedbackContainer container = new FeedbackContainer(fbList);
        
        Map map = new Map("East Campus");
        Building Bbuilding = new Building("B Building");
        Building GBuilding = new Building("G Building");
        Floor Bfirst = new Floor("First Floor",0);
        Floor Bsecond = new Floor("Second Floor",1);
        Room B1_102 = new Room("B102");
        Room B1_103 = new Room("B103");
        Room B1_201 = new Room("B201");
        MapObject vending = new MapObject("B First Floor Vending Machine",0,true);
        
        map.setX(50);
        map.setY(50);
        map.setWidth(100);
        map.setHeight(50);
        map.setColor(Color.WHITE);
        map.setEntranceX(70);
        map.setEntranceY(50);

        Bbuilding.setX(50);
        Bbuilding.setY(50);
        Bbuilding.setWidth(100);
        Bbuilding.setHeight(50);
        Bbuilding.setColor(Color.WHITE);
        Bbuilding.setEntranceX(70);
        Bbuilding.setEntranceY(50);

        GBuilding.setX(200);
        GBuilding.setY(250);
        GBuilding.setWidth(100);
        GBuilding.setHeight(50);
        GBuilding.setColor(Color.WHITE);
        GBuilding.setEntranceX(270);
        GBuilding.setEntranceY(250);
        
        map.addSpace(Bbuilding);
        map.addSpace(GBuilding);
        map.addFeedBackContainer(container);
        Bbuilding.addSpace(Bfirst);
        Bbuilding.addSpace(Bsecond);
        Bfirst.addSpace(B1_102);
        Bfirst.addSpace(B1_103);
        Bsecond.addSpace(B1_201);
        Controller controller = new Controller();
        controller.addMap(map);
        controller.setCurrentMap("East Campus");
        controller.save();
        LoadSave loadSave = new LoadSave();
        ArrayList<Map> loadedMaps = loadSave.loadSaves();
        Map loaded = loadedMaps.get(0);
        //System.out.println(loaded.getName());
        //System.out.println("Loaded map data \n" + loaded.contents.get(0).contents.get(0).getDirections());
        Search search = new Search();
        search.initializeSearchTree(loaded);
        
        ArrayList<Space> start = controller.search("B102");
        ArrayList<Space> destination = controller.search("B201");
        ArrayList<Space> a = controller.search("B");
        /*
        System.out.println("///////////////////////////////////////////////////////////");
        System.out.println(controller.getDirections(start.get(0),destination.get(0)));
        System.out.println(a);
        System.out.println("///////////////////////////////////////////////////////////");
        ArrayList<Space> found = controller.nearestMapObjects(a.get(1),0);
        //System.out.println(search.search("B102").getDirections());
        //System.out.println("\nSearch result: " + search.search("B"));
        Login login = new Login();
        login.createAccount("Boraborabora", "abcd", 2);
        login.createAccount("Bora", "abcd", 1);
        login.createAccount("Kenan","editor",1);
        login.createAccount("KenanAdmin", "admin", 2);
        System.out.println(login.login("Boraborabora", "abcd"));
        System.out.println(login.login("Boraborabora", "abc"));
        System.out.println(login.getUserList())*/

    }
}
