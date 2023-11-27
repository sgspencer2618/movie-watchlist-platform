package data_store;

import java.util.List;

import entity.Movie;

public interface WatchlistDBInterface {
    public List<Movie> getWatchlist (String username);

    public void addToWatchlist (String username, String movieID);

    public void removeFromWatchlist (String username, String movieID);
}
