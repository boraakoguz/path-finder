import java.util.ArrayList;

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
    

}
