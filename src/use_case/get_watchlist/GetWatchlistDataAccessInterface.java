package use_case.get_watchlist;

import entity.Movie;
import entity.User;
import entity.Watchlist;

import java.util.List;

public interface GetWatchlistDataAccessInterface {
    public Watchlist getWatchlist(String user);
}
