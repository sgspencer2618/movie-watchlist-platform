import org.json.JSONObject;
import org.junit.Test;
import utility.OMDBCaller;
import entity.Movie;

import java.util.List;
import java.util.Objects;

public class OMDBApiTest {
    @Test
    public void testSearch() {
        String search = "Harry Potter";
        OMDBCaller caller = new OMDBCaller();
        List<Movie> movies = caller.getSearch(search, 1);
        assert !movies.isEmpty();
        for (Movie elem: movies) {
            // Should have info
            assert !elem.getTitle().isEmpty();
            assert !elem.get.isEmpty();
            assert !elem.getPosterLink().isEmpty();
        }
    }
    @Test
    public void testMovie() {
        String imdb = "tt1745960"; // Top Gun Maverick
        OMDBCaller caller = new OMDBCaller();
        JSONObject json = caller.getMovie(imdb);
        assert Objects.equals(json.get("Title").toString(), "Top Gun: Maverick");
        assert Objects.equals(json.get("Year").toString(), "2022");
        assert Objects.equals(json.get("imdbID").toString(), imdb);
    }
}
