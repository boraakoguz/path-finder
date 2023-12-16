package UI;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

import Building.Map;
import Building.Search;
import Building.Space;
import Utilities.Feedback;
import Utilities.FeedbackContainer;
import Utilities.LoadSave;
import Utilities.Login;
import Utilities.User;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;

public class Controller {
    LoadSave loadSave;
    Search search;
    ArrayList<Map> maps;
    ArrayList<Feedback> feedBackList;
    Map currentMap;
    Login login;
    private int authLevel = 0; // 0-> User, 1 -> Editor, 2-> Admin
    public Controller(){
        this.loadSave = new LoadSave();
        this.search = new Search();
        this.maps = this.loadSave.loadSaves();

        try {
            this.login = new Login();
        } catch (FirebaseException e) {
        }
    }
    /**
     * This function gets a String input from the UI and sets the current context of the application
     * @param mapName string input
     */
    public void setCurrentMap(String mapName){
        for (Space space : maps) {
            if(space.getName().equals(mapName)){
                this.search.emptySearchTree();
                this.search.initializeSearchTree(space);
                this.feedBackList = ((Map)space).getFeedbackContainer().getFeedBackList();
                this.currentMap = (Map)space;
            }
        }
    }
    /**
     * Returns the loaded and available map names
     * @return
     */
    public ArrayList<String> getAvailableMaps(){
        ArrayList<String> mapNames = new ArrayList<String>();
        for (Space map : this.maps) {
            mapNames.add(map.getName());
        }
        return mapNames;
    }
    /**
     * returns the feedback list of the selected map
     * @return
     */
    public ArrayList<Feedback> getFeedBackList(){
        return this.feedBackList;
    }
    /**
     * adds a feedback to the current map, gets String input content of the feedback
     * @param content
     */
    public void addFeedBack(String content,String map,String building, String floor){
        Feedback newFeedback = new Feedback(content,map,building,floor, true);
        this.feedBackList.add(newFeedback);
        FeedbackContainer container = this.currentMap.getFeedbackContainer();
        container.addFeedBack(newFeedback);
        this.currentMap.addFeedBackContainer(container);
    }
    /**
     * Searches the given object in the current map
     * @param name String name of the target
     * @return Space object, null if not found.
     */
    public ArrayList<Space> search(String name){
        return this.search.search(name);
    }
    /**
     * searches nearest selected MapObject to your current location
     * @param currentLocation
     * @param objectType
     * @return
     */
    public ArrayList<Space> nearestMapObjects(Space currentLocation, int objectType){
        return this.search.nearestMapObject(currentLocation, objectType);
    }
    /**
     * Returns the arraylist of Space objects needed to be visited
     * in order to reach to destination from target
     * @param start A space object, start
     * @param target A space object, destination
     * @return
     */
    public ArrayList<Space> getDirections(Space start, Space target){
        ArrayList<Space> result = new ArrayList<Space>();
        ArrayList<Space> startPath = start.getDirections();
        ArrayList<Space> targetPath = target.getDirections();
        ArrayList<Space> tempSecondPartOfPath = new ArrayList<Space>();
        int i = 0;
        int j = 0;
        while(i<startPath.size()){
            j = 0;
            result.add(startPath.get(i));
            tempSecondPartOfPath.clear();
            while(j<targetPath.size()){            
                if(startPath.get(i).equals(targetPath.get(j))){
                    Collections.reverse(tempSecondPartOfPath);
                    result.addAll(tempSecondPartOfPath);
                    return result;
                }
                tempSecondPartOfPath.add(targetPath.get(j));
                j++;
            }
            i++;
        }
        return null;
    }
    public ArrayList<User> getUserList(){
        try {
            return this.login.getUserList();
        } catch (UnsupportedEncodingException | FirebaseException e) {
        }
        return null;
    }
    public int login(String username, String password){
        try {
            return this.login.login(username, password);
        } catch (UnsupportedEncodingException | FirebaseException e) {
        }
        return 0;
    }
    public void createAccount(String username, String password, int auth){
        try {
            this.login.createAccount(username, password, auth);
        } catch (UnsupportedEncodingException | FirebaseException | JacksonUtilityException e) {
        }
    }
    public void removeAccount(String username){
        try {
            this.login.removeAccount(username);
        } catch (UnsupportedEncodingException | FirebaseException e) {
        }
    }
}
