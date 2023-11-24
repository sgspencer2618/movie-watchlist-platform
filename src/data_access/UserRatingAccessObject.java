package data_access;
import entity.Movie;
import org.json.JSONObject;
import use_case.get_ratings.GetRatingsDataAccessInterface;
import use_case.remove_rating.RemoveRatingDataAccessInterface;
import use_case.search.SearchUserRatingsDataAccessInterface;
import use_case.update_rating.UpdateRatingDataAccessInterface;
import utility.ApiInterface;
import utility.OMDBCaller;

import javax.print.attribute.HashAttributeSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ApiInterface APIcaller = new OMDBCaller();
        HashMap<Movie, Integer> ratings = new HashMap<Movie, Integer>();
        ratings.put(APIcaller.getMovie("tt1375666"), 5); // Inception
        ratings.put(APIcaller.getMovie("tt10648342"), 1); // Thor: Love and Thunder
        ratings.put(APIcaller.getMovie("tt2935510"), 3); // Ad Astra
        return ratings;

    }
}
