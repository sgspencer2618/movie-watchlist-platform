package interface_adapters.get_watchlist;

import entity.Movie;
import entity.UserRating;
import entity.Watchlist;
import interface_adapters.defaultState;

import java.util.HashMap;
import java.util.List;

public class GetWatchlistState extends defaultState {

    private Watchlist watchlist;
    private List<UserRating> ratings;
    private List<Movie> movieList;

    public GetWatchlistState(GetWatchlistState copy) {
        this.watchlist = copy.watchlist;
        this.ratings = copy.ratings;
        this.movieList = copy.movieList;
    }

    GetWatchlistState() {}

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
    }

    public List<UserRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<UserRating> ratings) {
        this.ratings = ratings;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
