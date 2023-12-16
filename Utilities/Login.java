package Utilities;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.gson.JsonObject;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import net.thegreshams.firebase4j.service.Firebase;

public class Login {
    private static Firebase firebase;
    public Login() throws FirebaseException{
        firebase = new Firebase( "https://path-finder-99897-default-rtdb.europe-west1.firebasedatabase.app/" );
    }

    public void createAccount(String name, String password, int auth) throws UnsupportedEncodingException, FirebaseException, JacksonUtilityException{
        Map<String, Object> accountInfo=new LinkedHashMap<String, Object>();
        Map<String, Object> newAccount=new LinkedHashMap<String, Object>();
        accountInfo.put( "Password", password);
        accountInfo.put( "Auth", auth);
		newAccount.put(name, accountInfo);
        firebase.patch(newAccount);
    }
    /**
     * returns 0 if wrong username, 1 if wrong password, 2 if succeful editor login, 3 if succesful admin login
     * @param username
     * @param password
     * @throws UnsupportedEncodingException
     * @throws FirebaseException
     */
    public int login(String username, String password) throws UnsupportedEncodingException, FirebaseException{
        FirebaseResponse response = firebase.get(username);
        if(!response.getRawBody().equals("null"))
        {
            JSONObject jsonObject = new JSONObject(response.getRawBody());
            if(password.equals(jsonObject.get("Password"))){
                return Integer.parseInt(jsonObject.get("Auth").toString()) + 1;
            }
            return 1;
        }
        return 0;
    }
    public void removeAccount(String username) throws UnsupportedEncodingException, FirebaseException{
        firebase.delete(username);
    }
    public ArrayList<User> getUserList() throws UnsupportedEncodingException, FirebaseException{
        FirebaseResponse response = firebase.get();
        ArrayList<User> userList = new ArrayList<User>();
        if(!response.getRawBody().equals("null"))
        {
            JSONObject jsonObject = new JSONObject(response.getRawBody());
            for(Object name : jsonObject.names()){
                int userAuth = Integer.parseInt(((JSONObject)jsonObject.get(name.toString())).get("Auth").toString());
                User user = new User(name.toString(), userAuth);
                userList.add(user);
            }
        }
        return userList;
    }
}
