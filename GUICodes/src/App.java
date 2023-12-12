// Transmitions between Frames will be here
// I have not started to this part yet 

public class App {
    int currentFrame=0;

    public App(){

    }
    
    public void changeFrame(int a){

        this.currentFrame=a;
    }
    public static void main(String[] args) throws Exception {
        Path_Finder_Frame f=new Path_Finder_Frame();
        f.setVisible(true);
    }
}
