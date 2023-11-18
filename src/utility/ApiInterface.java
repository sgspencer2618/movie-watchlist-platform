package utility;

import java.util.List;
import entity.Movie;

public interface ApiInterface {
    public List<Movie> getSearch(String search, int page);

    public Movie getMovie(String imdbID);
}
