package use_case.search;

import entity.Movie;
import entity.UserRating;

import java.util.List;

public class SearchHandlerOutputData {
    List<Movie> movies;
    List<UserRating> ratings;
    public SearchHandlerOutputData(List<Movie> movies, List<UserRating> ratings) {
        this.movies = movies;
        this.ratings = ratings;
    };
}
