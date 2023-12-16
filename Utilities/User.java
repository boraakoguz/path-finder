package Utilities;

public class User {
    private String userName;
    private String userLevel;

    public User(String name,String level){
        userName=name;
        userLevel=level;
    }

    public String getuserName() { 
        return this.userName; 
    }
    
    public String getuserLevel() { 
        return this.userLevel; 
    }

    public String toString(){
        return this.userName + " Level: " + this.userLevel;
    }
}
