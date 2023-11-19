package use_case.add_to_watchlist;

import entity.Movie;
import entity.User;
import entity.Watchlist;

public interface AddToWatchlistDataAccessInterface {
    public Watchlist getWatchlist(String user);
}
