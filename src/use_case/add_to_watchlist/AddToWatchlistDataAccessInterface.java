package use_case.add_to_watchlist;

import entity.Movie;
import entity.User;

import java.util.List;

public interface AddToWatchlistDataAccessInterface {
    public List<Movie> getWatchlist(String user);
}
