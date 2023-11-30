package interface_adapters.get_ratings;

import entity.Movie;
import entity.UserRating;
import interface_adapters.defaultState;

import java.util.HashMap;
import java.util.List;

public class GetRatingsState extends defaultState {
    private List<UserRating> ratings;
    private String user;

    private List<Movie> movieList;

    public GetRatingsState(GetRatingsState copy) {
        this.ratings = copy.ratings;
        this.movieList = copy.movieList;
    }

    GetRatingsState() {}

    public List<UserRating> getRatings() {
        return ratings;
    }
    public String getUser() {
        return user;
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
