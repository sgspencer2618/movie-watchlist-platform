package data_access;
import entity.Movie;
import org.json.JSONObject;
import use_case.get_ratings.GetRatingsDataAccessInterface;
import use_case.remove_rating.RemoveRatingDataAccessInterface;
import use_case.search.SearchUserRatingsDataAccessInterface;
import use_case.update_rating.UpdateRatingDataAccessInterface;

import java.util.HashMap;
import java.util.List;

public class UserRatingAccessObject implements SearchUserRatingsDataAccessInterface, GetRatingsDataAccessInterface,
        RemoveRatingDataAccessInterface, UpdateRatingDataAccessInterface {

    public UserRatingAccessObject() {

    }
    public void removeRating(String username, String move_id){}
    public void updateRating(String username, String move_id, int newRating){}
    public int getUserRating(String user, String movieID){
        return 1;
    }
    public boolean userRatingExists(String user, String movieID){
        return false;
    }

    public HashMap<String, Integer> getUserRatingsHashmap(String user, List<Movie> movies){
        return new HashMap<String, Integer>();
    }

    public HashMap<Movie, Integer> getRatings(String user){
        return new HashMap<>();
    }
}
