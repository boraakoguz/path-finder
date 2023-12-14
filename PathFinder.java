import UI.App;
import UI.Controller;

public class PathFinder {
    Controller controller;
    App app;
    TestGUIert testErtugrul;
    public PathFinder(){
        this.controller = new Controller();
        this.app = new App();
        this.testErtugrul = new TestGUIert();

    }
    public static void main(String[] args) {
        new PathFinder();
    }
}
