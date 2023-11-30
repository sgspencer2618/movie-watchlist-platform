package use_case.remove_rating;

public interface RemoveRatingDataAccessInterface {
    public void removeRating(String username, String move_id);

    public boolean userRatingExists(String username, String move_id);

}
