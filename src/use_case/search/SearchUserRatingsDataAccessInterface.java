
package use_case.search;

public interface SearchUserRatingsDataAccessInterface {
    Integer getUserRating(String user, String movieID);
}