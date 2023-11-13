package use_case.movie_info;

import org.json.JSONObject;

public interface MovieInfoDataAccessInterface {

    public JSONObject getMovie(String imdbID);
}
