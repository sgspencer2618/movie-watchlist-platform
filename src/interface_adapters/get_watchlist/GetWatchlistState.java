package interface_adapters.get_watchlist;

import entity.Movie;
import entity.Watchlist;

import java.util.HashMap;

public class GetWatchlistState {

    private Watchlist watchlist;
    private HashMap<Movie, Integer> ratings;

    public GetWatchlistState(GetWatchlistState copy) {
        this.watchlist = copy.watchlist;
        this.ratings = copy.ratings;
    }

    GetWatchlistState() {}

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
    }

    public HashMap<Movie, Integer> getRatings() {
        return ratings;
    }

    public void setRatings(HashMap<Movie, Integer> ratings) {
        this.ratings = ratings;
    }
}
