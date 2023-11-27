package use_case.get_watchlist;

import entity.Movie;
import entity.UserRating;
import entity.Watchlist;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GetWatchlistOutputData {
    private final Watchlist watchlist;
    private final List<UserRating> ratings;
    private final List<Movie> movieList;

    public GetWatchlistOutputData(Watchlist watchlist, List<UserRating> ratings, List<Movie> movieList) {
        this.watchlist = watchlist;
        this.ratings = ratings;
        this.movieList = movieList;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public List<UserRating> getRatings() {
        return ratings;
    }
    public List<Movie> getMovieList() {
        return movieList;
    }
}
