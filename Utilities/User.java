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
    
    public int getuserLevel() { 
        return this.userLevel; 
    }

    public String toString(){
        return this.userName + " Level: " + this.userLevel;
    }
}
