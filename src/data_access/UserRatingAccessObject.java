package data_access;
import org.json.JSONObject;
import use_case.search.SearchUserRatingsDataAccessInterface;

import java.util.HashMap;

public class UserRatingAccessObject implements SearchUserRatingsDataAccessInterface{

    public UserRatingAccessObject() {

    }

    public Integer getUserRating(String user, Integer movieID){
        return 1;
    }

    public HashMap<Integer, Integer> getUserRatingsHashmap(String user, JSONObject movies){
        return new HashMap<Integer, Integer>();
    }
}
