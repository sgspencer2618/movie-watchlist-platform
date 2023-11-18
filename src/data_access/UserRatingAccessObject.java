package data_access;
import entity.Movie;
import org.json.JSONObject;
import use_case.get_ratings.GetRatingsDataAccessInterface;
import use_case.search.SearchUserRatingsDataAccessInterface;

import java.util.HashMap;
import java.util.List;

public class UserRatingAccessObject implements SearchUserRatingsDataAccessInterface, GetRatingsDataAccessInterface {

    public UserRatingAccessObject() {

    }

    public Integer getUserRating(String user, String movieID){
        return 1;
    }

    public HashMap<String, Integer> getUserRatingsHashmap(String user, List<Movie> movies){
        return new HashMap<String, Integer>();
    }

    public HashMap<Movie, Integer> getRatings(String user){
        return new HashMap<>();
    }
}
