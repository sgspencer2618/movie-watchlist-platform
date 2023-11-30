package use_case.search;

import entity.Movie;
import entity.UserRating;
import entity.Watchlist;

import java.util.List;

public class SearchHandlerOutputData {
    private List<Movie> movies;
    private List<UserRating> ratings;
    private Watchlist watchlist;
    public SearchHandlerOutputData(List<Movie> movies, List<UserRating> ratings, Watchlist watchlist) {
        this.movies = movies;
        this.ratings = ratings;
        this.watchlist = watchlist;
    };

    public List<Movie> getMovieList() {
        return movies;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public List<UserRating> getRatings() {
        return ratings;
    }
}
