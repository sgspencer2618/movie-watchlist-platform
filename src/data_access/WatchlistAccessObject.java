package data_access;

import entity.Movie;
import entity.User;
import use_case.add_to_watchlist.AddToWatchlistDataAccessInterface;
import use_case.get_watchlist.GetWatchlistDataAccessInterface;

import java.util.List;

public class WatchlistAccessObject implements AddToWatchlistDataAccessInterface, GetWatchlistDataAccessInterface {

    @Override
    public List<Movie> getWatchlist(String user) {
        return null;
    }
}
