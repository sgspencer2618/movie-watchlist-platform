
package use_case.search;

public interface SearchUserRatingsDataAccessInterface {
    int getUserRating(String user, String movieID);
}