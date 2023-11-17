package use_case.update_rating;
import entity.UserRating;

public interface UpdateRatingDataAccessInterface {
    public void updateRating(String username, String move_id, int newRating);
}
