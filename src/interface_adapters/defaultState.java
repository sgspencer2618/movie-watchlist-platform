package interface_adapters;

import java.util.List;

import entity.Movie;
import entity.UserRating;
import entity.Watchlist;
public abstract class defaultState {
    // Every view that has these three should just extend this abstract class.
    private List<Movie> movies;
    private List<UserRating> ratings;
    private Watchlist watchlist;

    public List<UserRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<UserRating> ratings) {
        this.ratings = ratings;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
    }

    public List<Movie> getMovieList() {
        return movies;
    }

    public void setMovieList(List<Movie> movies) {
        this.movies = movies;
    }
}
