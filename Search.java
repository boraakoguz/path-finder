public class Search {
    final SearchNode root = new SearchNode("");
    public Search(Space map){
        iterateSearchTree(map);
    }
    public void iterateSearchTree(Space map){
        fillSearchTree(map);
        for (Space children : map.getContents()) {
            iterateSearchTree(children);
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
    public Space search(String name){
        SearchNode start = this.root;
        for(int i = 1; i<=name.length();i++){
            String substring = name.substring(0, i);
            if(start.getChildren().containsKey(substring)){
                start = start.getChildren().get(substring);
            }
            else{
                return null;
            }
        }
        return start.getSpace();
    }
}
