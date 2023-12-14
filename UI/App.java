package UI;
// Transmitions between Frames will be here
// I have not started to this part yet 

public class App {
    int currentFrame=0;

    public App(){
        Path_Finder_Frame f=new Path_Finder_Frame();
        f.setVisible(true);
    }
    
    public void changeFrame(int a){

        this.currentFrame=a;
    }
}
