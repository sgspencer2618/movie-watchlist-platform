import org.json.JSONObject;
import org.junit.Test;
import utility.OMDPCaller;

import java.util.Objects;

public class OMDBApiTest {
    @Test
    public void testSearch() {
        String search = "Harry Potter";
        OMDPCaller caller = new OMDPCaller();
        JSONObject json = caller.getSearch(search, 1);
        assert !json.isEmpty();
    }
    @Test
    public void testMovie() {
        String imdb = "tt1745960"; // Top Gun Maverick
        OMDPCaller caller = new OMDPCaller();
        JSONObject json = caller.getMovie(imdb);
        assert Objects.equals(json.get("Title").toString(), "Top Gun: Maverick");
        assert Objects.equals(json.get("Year").toString(), "2022");
        assert Objects.equals(json.get("imdbID").toString(), imdb);
    }
}
