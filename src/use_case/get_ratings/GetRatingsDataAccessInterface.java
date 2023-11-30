package use_case.get_ratings;

import entity.UserRating;
import java.util.List;

public interface GetRatingsDataAccessInterface {
    public List<UserRating> getRatings(String user);
}
