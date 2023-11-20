package use_case.remove_rating;

import java.util.List;
import entity.UserRating;

public interface RemoveRatingDataAccessInterface {
    public void removeRating(String username, String move_id);

    public boolean userRatingExists(String username, String move_id);

    public int getUserRating(String user, String movieID);
}
