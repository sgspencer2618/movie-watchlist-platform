package use_case.remove_from_watchlist;

import entity.Watchlist;

public interface RemoveFromWatchlistDataAccessInterface {

    public boolean removeFromWatchlist(String username, String imdbID);

}
