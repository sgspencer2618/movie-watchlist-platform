package use_case.get_watchlist;

import entity.Watchlist;



public interface GetWatchlistDataAccessInterface {
    public Watchlist getWatchlist(String user);
}
