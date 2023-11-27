//Data_Access
package data_access;
import entity.User;

import java.util.*;


public class UserAccessObject {

    public UserAccessObject(){

    }
    private final Map<String, User> accounts = new HashMap<>();



    public String getCurrUser(){
        return "";
    }

    public User get(String username) {
        return accounts.get(username);
    }
}
