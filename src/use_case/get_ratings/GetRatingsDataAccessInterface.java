package use_case.get_ratings;

import entity.Movie;
import entity.UserRating;
import view.RatingsView;

import java.util.HashMap;
import java.util.List;

public interface GetRatingsDataAccessInterface {
    public List<UserRating> getRatings(String user);
}
