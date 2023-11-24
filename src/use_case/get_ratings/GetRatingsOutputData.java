package use_case.get_ratings;

import entity.Movie;

import java.util.HashMap;
import java.util.List;

public class GetRatingsOutputData {
    private final HashMap<Movie, Integer> ratings;

    private final List<Movie> movieList;

    public GetRatingsOutputData(HashMap<Movie, Integer> ratings, List<Movie> movieList) {
        this.ratings = ratings;
        this.movieList = movieList;
    }

    public List<Movie> getMovieList() {return movieList;}

    public HashMap<Movie, Integer> getRatings() {return ratings;}
}
