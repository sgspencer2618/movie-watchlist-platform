package use_case.remove_from_watchlist;


public interface RemoveFromWatchlistDataAccessInterface {

    public boolean removeFromWatchlist(String username, String imdbID);

}
