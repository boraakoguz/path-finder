package UI;

import Utilities.Feedback;

// this class is like a controller class. every frame changes happen here
public class Path_Finder_Frame{
    Controller backendController;
    User_Frame user;
    Login_Frame login;
    CFeedback_Frame feedBack;
    Admin_Welcome_Page adWelcome;
    Editor_Welcome_Page edWelcome;
    Admin_Userlist_Frame userlist;
    Admin_AddUser_Frame adduser;
    Editor_SeeFeedback_Frame seefeed;
    Editor_DeatiledFeedback_Frame detailFeed;
    AdminMapTools adminMapTools;
    EditorMapTools editorMapTools;

    public Path_Finder_Frame(){
        backendController = new Controller();
        user=new User_Frame(this,backendController);
        login=new Login_Frame(this,backendController);
        feedBack=new CFeedback_Frame(this,backendController);
        adWelcome=new Admin_Welcome_Page(this,backendController);
        adduser=new Admin_AddUser_Frame(this,backendController);
        edWelcome=new Editor_Welcome_Page(this,backendController);
        userlist=new Admin_Userlist_Frame(this,backendController);
        seefeed=new Editor_SeeFeedback_Frame(this,backendController);
        Feedback dummyFeedback = new Feedback("","","","",   "", "", "", false);
        detailFeed=new Editor_DeatiledFeedback_Frame(this,dummyFeedback,null);
        adminMapTools = new AdminMapTools(this.backendController,this);
        editorMapTools = new EditorMapTools(this.backendController,this);
        user.setVisible(true);

        //TODO: DELETE IT TEST
        //changeFrame(7);
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
            adminMapTools.setVisible(false);
            editorMapTools.setVisible(false);
            //going back to main page
        }
        else if(i==1){
            user.setVisible(false);
            login.setVisible(true);
            feedBack.setVisible(false);
            adWelcome.setVisible(false);
            edWelcome.setVisible(false);
            userlist.setVisible(false);
            seefeed.setVisible(false);
            adduser.setVisible(false);
            detailFeed.setVisible(false);
            adminMapTools.setVisible(false);
            editorMapTools.setVisible(false);
            //going to login page
        }
        else if(i==2){
            user.setVisible(false);
            login.setVisible(false);
            feedBack.setVisible(true);
            adWelcome.setVisible(false);
            edWelcome.setVisible(false);
            userlist.setVisible(false);
            seefeed.setVisible(false);
            adduser.setVisible(false);
            detailFeed.setVisible(false);
            adminMapTools.setVisible(false);
            editorMapTools.setVisible(false);
            //going to create feedback page
        }
        else if(i==3){
            user.setVisible(false);
            login.setVisible(false);
            feedBack.setVisible(false);
            adWelcome.setVisible(true);
            edWelcome.setVisible(false);
            userlist.setVisible(false);
            seefeed.setVisible(false);
            adduser.setVisible(false);
            detailFeed.setVisible(false);
            adminMapTools.setVisible(false);
            editorMapTools.setVisible(false);
            // goint to admin welcome page
        }
        else if(i==4){
            user.setVisible(false);
            login.setVisible(false);
            feedBack.setVisible(false);
            adWelcome.setVisible(false);
            edWelcome.setVisible(true);
            userlist.setVisible(false);
            seefeed.setVisible(false);
            adduser.setVisible(false);
            detailFeed.setVisible(false);
            adminMapTools.setVisible(false);
            editorMapTools.setVisible(false);
            // going to editor welcome page
        }
        else if(i==5){
            user.setVisible(false);
            login.setVisible(false);
            feedBack.setVisible(false);
            adWelcome.setVisible(false);
            edWelcome.setVisible(false);
            userlist.setVisible(false);
            seefeed.setVisible(false);
            adduser.setVisible(true);
            detailFeed.setVisible(false);
            adminMapTools.setVisible(false);
            editorMapTools.setVisible(false);
            //going to add user page
        }
        else if(i==6){
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
            adminMapTools.setVisible(false);
            editorMapTools.setVisible(false);
            //going to userlist page
        }
        else if(i==7){
            user.setVisible(false);
            login.setVisible(false);
            feedBack.setVisible(false);
            adWelcome.setVisible(false);
            edWelcome.setVisible(false);
            userlist.setVisible(false);
            seefeed.setVisible(false);
            adduser.setVisible(false);
            detailFeed.setVisible(false);
            adminMapTools.setVisible(true);
            editorMapTools.setVisible(false);
            //going to admin map tools page 
        }
        else if(i==8){
            user.setVisible(false);
            login.setVisible(false);
            feedBack.setVisible(false);
            adWelcome.setVisible(false);
            edWelcome.setVisible(false);
            userlist.setVisible(false);
            seefeed.refreshFeedBackList();
            seefeed.setVisible(true);
            adduser.setVisible(false);
            detailFeed.setVisible(false);
            adminMapTools.setVisible(false);
            editorMapTools.setVisible(false);
            //going to editors' seefeedback page
        }
        else if(i==9){
            user.setVisible(false);
            login.setVisible(false);
            feedBack.setVisible(false);
            adWelcome.setVisible(false);
            edWelcome.setVisible(false);
            userlist.setVisible(false);
            seefeed.setVisible(false);
            adduser.setVisible(false);
            detailFeed.setVisible(false);
            adminMapTools.setVisible(false);
            editorMapTools.setVisible(true);
        }
        
    }
    public void setEditorName(String name){
        this.edWelcome.setEditorName(name);
    }
    public void setAdminName(String name){
        this.adWelcome.setAdminName(name);
    }    
}
