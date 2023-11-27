package use_case.get_ratings;

import entity.Movie;
import entity.UserRating;

import java.util.HashMap;
import java.util.List;

public class GetRatingsOutputData {
    private final List<UserRating> ratings;

    private final List<Movie> movieList;

    public GetRatingsOutputData(List<UserRating> ratings, List<Movie> movieList) {
        this.ratings = ratings;
        this.movieList = movieList;
    }

    public List<Movie> getMovieList() {return movieList;}

    public List<UserRating> getRatings() {return ratings;}
}
