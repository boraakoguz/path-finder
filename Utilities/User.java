package Utilities;

public class User {
    private String userName;
    private int userLevel;

    public User(String name,int level){
        userName=name;
        userLevel=level;
    }

    public String getuserName() { 
        return this.userName; 
    }
    
    public String getuserLevel() { 
        if(this.userLevel == 1){
            return "Editor";
        }
        return "Admin"; 
    }

    public String toString(){
        return this.userName + " Level: " + this.userLevel;
    }
}
