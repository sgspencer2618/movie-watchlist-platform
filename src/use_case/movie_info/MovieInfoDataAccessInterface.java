package use_case.movie_info;

import entity.Movie;
import org.json.JSONObject;

public interface MovieInfoDataAccessInterface {

    public Movie getMovie(String imdbID);
}
