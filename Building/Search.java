package Building;
import java.util.ArrayList;
import java.util.HashMap;

public class Search {
    final SearchNode root = new SearchNode("");
    public Search(){
    }

    public void initializeSearchTree(Space map){
        fillSearchTree(map);
        for (Space children : map.getContents()) {
            initializeSearchTree(children);
        }
    }
    public void fillSearchTree(Space map){
        SearchNode start = this.root;
        String name = map.getName();
        for(int i = 0; i< name.length(); i++){
            String substring = name.substring(0, i+1);
            if(start.getChildren().containsKey(substring)){
                start = start.getChildren().get(substring);
            }
            else{
                SearchNode newNode = new SearchNode(substring);
                start.getChildren().put(substring, newNode);
                start = start.getChildren().get(substring);
            }
        }
        start.addSpace(map);
    }
    public ArrayList<Space> search(String name){
        ArrayList<Space> searchResults = new ArrayList<Space>();
        SearchNode start = this.root;
        for(int i = 1; i<=name.length();i++){
            String substring = name.substring(0, i);
            if(start.getChildren().containsKey(substring)){
                start = start.getChildren().get(substring);
            }
            else{
                break;
            }
        }
        searchResults.add(start.getSpace());
        searchResults.addAll(recommendations(start));
        return searchResults;
    }
    public ArrayList<Space> nearestMapObject(Space currentLocation, int type){
        ArrayList<Space> results = new ArrayList<Space>();
        ArrayList<Space> parentQueue = new ArrayList<Space>();
        HashMap<Space,Boolean> alreadyChecked = new HashMap<Space,Boolean>();
        parentQueue.add(currentLocation);
        while(!parentQueue.isEmpty()){
            Space current = parentQueue.remove(0);
            if(current == null){
                break;
            }
            ArrayList<Space> childrenQueue = new ArrayList<Space>();
            childrenQueue.addAll(current.getContents());
            while(!childrenQueue.isEmpty()){
                Space child = childrenQueue.remove(0);
                if(alreadyChecked.containsKey(child)){
                    continue;
                }
                alreadyChecked.put(child, true);
                if(child instanceof MapObject && ((MapObject)child).getType() == type){
                    results.add(child);
                    if(results.size()>2){
                        return results;
                    }
                }
                childrenQueue.addAll(child.getContents());
            }
            parentQueue.add(current.getParent());
        }
        return results;
    }
    public SearchNode getSearchNode(Space target){
        SearchNode start = this.root;
        String name = target.getName();
        for(int i = 1; i<=name.length();i++){
            String substring = name.substring(0, i);
            if(start.getChildren().containsKey(substring)){
                start = start.getChildren().get(substring);
            }
            else{
                return null;
            }
        }
        return start;
    }
    public ArrayList<Space> recommendations(SearchNode node){
        ArrayList<Space> recommendations = new ArrayList<Space>();
        ArrayList<SearchNode> queue = new ArrayList<SearchNode>();
        queue.add(node);
        while(!queue.isEmpty()){
            SearchNode current = queue.remove(0);
            if(current.hasSpace()){
                recommendations.add(current.getSpace());
                if(recommendations.size() >= 3){
                    return recommendations;
                }
            }
            HashMap<String,SearchNode> nextValues = current.getChildren();
            for (SearchNode nextNodes : nextValues.values()) {
                queue.add(nextNodes);
            }
        }
        return recommendations;
    }
    public void emptySearchTree(){
        this.root.children.clear();
    }
}
