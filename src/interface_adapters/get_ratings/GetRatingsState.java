package interface_adapters.get_ratings;

import entity.Movie;

import java.util.HashMap;
import java.util.List;

public class GetRatingsState {

    private HashMap<Movie, Integer> ratings;

    private List<Movie> movieList;

    public GetRatingsState(GetRatingsState copy) {
        this.ratings = copy.ratings;
        this.movieList = copy.movieList;
    }

    GetRatingsState() {}

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
