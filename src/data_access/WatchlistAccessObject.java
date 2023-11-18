package data_access;

import entity.Movie;
import use_case.add_to_watchlist.AddToWatchlistDataAccessInterface;

import java.util.List;

public class WatchlistAccessObject implements AddToWatchlistDataAccessInterface {

    @Override
    public List<Movie> getWatchlist(String user) {
        return null;
    }
}
