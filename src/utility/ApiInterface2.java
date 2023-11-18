package utility;

import entity.Movie;
import entity.MovieDetailed;

import java.util.List;

public interface ApiInterface2 {
    public List<Movie> getSearch(String search, int page);

    public MovieDetailed getMovie(String imdbID);
}
