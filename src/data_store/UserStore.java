package data_store;

import java.io.Serializable;
import java.util.HashMap;

public class UserStore implements Serializable {

    private final String username;

    private final String password;


    private HashMap<String, UserMovie> userMovies;

    public UserStore(String username, String password){
        this.username = username;
        this.password = password;
        this.userMovies = new HashMap<>();
    }


    public String getUsername(){return username;}

    public String getPassword(){return password;}

    public HashMap<String, UserMovie> getUserMovies(){return userMovies;}

    public void setUserMovies(HashMap<String, UserMovie> userMovies) {
        this.userMovies = userMovies;
    }


}
