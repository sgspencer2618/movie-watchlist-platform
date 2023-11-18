package use_case.search;

public interface SearchUserWatchlistDataAccessInterface {
    Boolean inWatchlist(String user, Integer MovieID);
}
