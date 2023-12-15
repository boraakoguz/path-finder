package Utilities;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

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
		newAccount.put(name, accountInfo);
        firebase.patch(newAccount);
    }
    public static void deleteAccount(String username) throws UnsupportedEncodingException, FirebaseException{
        firebase.delete(username);
    }
    public boolean login(String username, String password, int auth) throws UnsupportedEncodingException, FirebaseException{
        FirebaseResponse response = firebase.get(username);
        return false;
    }
}
