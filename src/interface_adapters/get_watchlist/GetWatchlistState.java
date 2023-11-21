package interface_adapters.get_watchlist;

import entity.Movie;
import entity.Watchlist;

import java.util.HashMap;
import java.util.List;

public class GetWatchlistState {

    private Watchlist watchlist;
    private HashMap<Movie, Integer> ratings;
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

    public HashMap<Movie, Integer> getRatings() {
        return ratings;
    }

    public void setRatings(HashMap<Movie, Integer> ratings) {
        this.ratings = ratings;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
