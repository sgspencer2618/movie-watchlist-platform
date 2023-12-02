package interface_adapters.get_ratings;

import entity.Movie;
import entity.UserRating;
import interface_adapters.defaultState;

import java.util.List;

public class GetRatingsState extends defaultState {
    private List<UserRating> ratings;

    private List<Movie> movieList;

    GetRatingsState() {}

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
