package UI;


public class Path_Finder_Frame{
    User_Frame user;
    Login_Frame login;
    CFeedback_Frame feedBack;

    Path_Finder_Frame(){
        user=new User_Frame(this);
        login=new Login_Frame(this);
        feedBack=new CFeedback_Frame(this);
        user.setVisible(true);
    }
    protected void changeFrame(int i){
        if(i==1){
            user.setVisible(false);
            login.setVisible(true);
            feedBack.setVisible(false);
        }
        if(i==2){
            user.setVisible(false);
            login.setVisible(false);
            feedBack.setVisible(true);
        }
    }
    public static void main(String[] args) {
        Path_Finder_Frame f=new Path_Finder_Frame();
    }
    
}
