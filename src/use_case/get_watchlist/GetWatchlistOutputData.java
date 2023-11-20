package use_case.get_watchlist;

import entity.Movie;
import entity.Watchlist;

import java.util.HashMap;

public class GetWatchlistOutputData {
    private final Watchlist watchlist;
    private final HashMap<Movie, Integer> ratings;

    public GetWatchlistOutputData(Watchlist watchlist, HashMap<Movie, Integer> ratings) {
        this.watchlist = watchlist;
        this.ratings = ratings;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public HashMap<Movie, Integer> getRatings() {
        return ratings;
    }
}
