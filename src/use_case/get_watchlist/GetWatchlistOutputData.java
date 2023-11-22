package use_case.get_watchlist;

import entity.Movie;
import entity.Watchlist;

import java.util.HashMap;
import java.util.List;

public class GetWatchlistOutputData {
    private final Watchlist watchlist;
    private final HashMap<Movie, Integer> ratings;
    private final List<Movie> movieList;

    public GetWatchlistOutputData(Watchlist watchlist, HashMap<Movie, Integer> ratings, List<Movie> movieList) {
        this.watchlist = watchlist;
        this.ratings = ratings;
        this.movieList = movieList;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public HashMap<Movie, Integer> getRatings() {
        return ratings;
    }
    public List<Movie> getMovieList() {
        return movieList;
    }
}
