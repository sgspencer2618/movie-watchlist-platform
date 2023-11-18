package utility;

import entity.Movie;

import java.util.List;

public interface ApiInterface2 {
    public List<Movie> getSearch(String search, int page);

    public Movie getMovie(String imdbID);
}
