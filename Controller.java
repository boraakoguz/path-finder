import java.util.ArrayList;
import java.util.Collections;

public class Controller {
    LoadSave loadSave;
    Search search;
    ArrayList<Map> maps;
    public Controller(){
        this.loadSave = new LoadSave();
        this.search = new Search();
        this.maps = this.loadSave.loadSaves();
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
    
    public Space search(String name){
        return this.search.search(name);
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
}
