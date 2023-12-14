import UI.App;
import UI.Controller;

public class PathFinder {
    Controller controller;
    App app;
    public PathFinder(){
        this.controller = new Controller();
        this.app = new App();

    }
    public static void main(String[] args) {
        new PathFinder();
    }
}
