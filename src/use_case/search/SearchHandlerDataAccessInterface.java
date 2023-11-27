package use_case.search;

import entity.UserRating;

public interface SearchHandlerDataAccessInterface {
    public UserRating getUserRating(String user, String movieID);
    public boolean userRatingExists(String user, String movieID);
}
