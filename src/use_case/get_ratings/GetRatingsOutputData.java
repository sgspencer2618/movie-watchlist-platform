package use_case.get_ratings;

import entity.Movie;
import entity.UserRating;
import entity.Watchlist;

import java.util.List;

public class GetRatingsOutputData {
    private final List<UserRating> ratings;

    private final List<Movie> movieList;

    private final Watchlist watchlist;

    public GetRatingsOutputData(List<UserRating> ratings, List<Movie> movieList, Watchlist watchlist) {
        this.ratings = ratings;
        this.movieList = movieList;
        this.watchlist = watchlist;
    }

    public List<Movie> getMovieList() {return movieList;}

    public List<UserRating> getRatings() {return ratings;}

    public Watchlist getWatchlist() {return watchlist;}
}
