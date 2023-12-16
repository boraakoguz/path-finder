package UI;

import Utilities.Feedback;
import Utilities.Login;

// this class is like a controller class. every frame changes happen here
public class Path_Finder_Frame{
    Controller backendController=new Controller();
    User_Frame user;
    Login_Frame login;
    CFeedback_Frame feedBack;
    Admin_Welcome_Page adWelcome;
    Editor_Welcome_Page edWelcome;
    Admin_Userlist_Frame userlist;
    Admin_AddUser_Frame adduser;
    Editor_SeeFeedback_Frame seefeed;
    Editor_DeatiledFeedback_Frame detailFeed;

    Path_Finder_Frame(){
        user=new User_Frame(this,backendController);
        login=new Login_Frame(this,backendController);
        feedBack=new CFeedback_Frame(this,backendController);
        adWelcome=new Admin_Welcome_Page(this,backendController);
        adduser=new Admin_AddUser_Frame(this,backendController);
        edWelcome=new Editor_Welcome_Page(this,backendController);
        userlist=new Admin_Userlist_Frame(this,backendController);
        seefeed=new Editor_SeeFeedback_Frame(this,backendController);
        //detailFeed=new Editor_DeatiledFeedback_Frame(this,null);
        user.setVisible(true);
    }
    protected void changeFrame(int i, Feedback f){
        if(i==10){
            user.setVisible(false);
            login.setVisible(false);
            feedBack.setVisible(false);
            adWelcome.setVisible(false);
            edWelcome.setVisible(false);
            userlist.setVisible(false);
            seefeed.setVisible(false);
            adduser.setVisible(false);
            detailFeed=new Editor_DeatiledFeedback_Frame(this,f,backendController);
            detailFeed.setVisible(true);

            //going to chosen feedback in order to read it detailed 
            //Editor seefeedback page should be added
        }
    }
    protected void changeFrame(int i){
        if(i==0){
            user.setVisible(true);
            login.setVisible(false);
            feedBack.setVisible(false);
            adWelcome.setVisible(false);
            edWelcome.setVisible(false);
            userlist.setVisible(false);
            seefeed.setVisible(false);
            adduser.setVisible(false);
            detailFeed.setVisible(false);
            //going back to main page
        }
        if(i==1){
            user.setVisible(false);
            login.setVisible(true);
            feedBack.setVisible(false);
            adWelcome.setVisible(false);
            edWelcome.setVisible(false);
            userlist.setVisible(false);
            seefeed.setVisible(false);
            adduser.setVisible(false);
            detailFeed.setVisible(false);
            //going to login page
        }
        if(i==2){
            user.setVisible(false);
            login.setVisible(false);
            feedBack.setVisible(true);
            adWelcome.setVisible(false);
            edWelcome.setVisible(false);
            userlist.setVisible(false);
            seefeed.setVisible(false);
            adduser.setVisible(false);
            detailFeed.setVisible(false);
            //going to create feedback page
        }
        if(i==3){
            user.setVisible(false);
            login.setVisible(false);
            feedBack.setVisible(false);
            adWelcome.setVisible(true);
            edWelcome.setVisible(false);
            userlist.setVisible(false);
            seefeed.setVisible(false);
            adduser.setVisible(false);
            detailFeed.setVisible(false);
            // goint to admin welcome page
        }
        if(i==4){
            user.setVisible(false);
            login.setVisible(false);
            feedBack.setVisible(false);
            adWelcome.setVisible(false);
            edWelcome.setVisible(true);
            userlist.setVisible(false);
            seefeed.setVisible(false);
            adduser.setVisible(false);
            detailFeed.setVisible(false);
            // going to editor welcome page
        }
        if(i==5){
            user.setVisible(false);
            login.setVisible(false);
            feedBack.setVisible(false);
            adWelcome.setVisible(false);
            edWelcome.setVisible(false);
            userlist.setVisible(false);
            seefeed.setVisible(false);
            adduser.setVisible(true);
            detailFeed.setVisible(false);
            //going to add user page
        }
        if(i==6){
            user.setVisible(false);
            login.setVisible(false);
            feedBack.setVisible(false);
            adWelcome.setVisible(false);
            edWelcome.setVisible(false);
            
            userlist.refreshUserList();
            userlist.setVisible(true);
            seefeed.setVisible(false);
            adduser.setVisible(false);
            detailFeed.setVisible(false);
            //going to userlist page
        }
        if(i==7){
            user.setVisible(false);
            login.setVisible(false);
            feedBack.setVisible(false);
            adWelcome.setVisible(false);
            edWelcome.setVisible(false);
            userlist.setVisible(false);
            seefeed.setVisible(false);
            adduser.setVisible(false);
            detailFeed.setVisible(false);
            //going to admin map tools page 
            //Admin map tools should be added
        }
        if(i==8){
            user.setVisible(false);
            login.setVisible(false);
            feedBack.setVisible(false);
            adWelcome.setVisible(false);
            edWelcome.setVisible(false);
            userlist.setVisible(false);
            seefeed.setVisible(true);
            adduser.setVisible(false);
            detailFeed.setVisible(false);
            //going to editors' seefeedback page
        }
        if(i==9){
            user.setVisible(false);
            login.setVisible(false);
            feedBack.setVisible(false);
            adWelcome.setVisible(false);
            edWelcome.setVisible(false);
            userlist.setVisible(false);
            seefeed.setVisible(false);
            adduser.setVisible(false);
            detailFeed.setVisible(false);
            //going to editor map tools page
            //Editor Maptools should be added
        }
        
    }
    public static void main(String[] args) {
        Path_Finder_Frame f=new Path_Finder_Frame();
    }
    
}
