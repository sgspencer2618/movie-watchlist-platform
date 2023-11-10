package utility;

import org.json.JSONObject;

public interface ApiInterface {
    public JSONObject getSearch(String search, int page);

    public JSONObject getMovie(String imdbID);
}
