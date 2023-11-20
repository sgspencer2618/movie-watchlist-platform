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
            assert !elem.getImdbID().isEmpty();
            assert !elem.getTitle().isEmpty();
            assert !elem.getPosterURL().isEmpty();
            assert !(elem.getYear() == 0); // No movie is made in year 0 (from my knowledge at least)
        }
    }
    @Test
    public void testMovie() {
        String imdb = "tt1745960"; // Top Gun Maverick
        OMDBCaller caller = new OMDBCaller();
        Movie movie = caller.getMovie(imdb);
        // All the same as before
        assert !movie.getImdbID().isEmpty();
        assert !movie.getTitle().isEmpty();
        assert !movie.getPosterURL().isEmpty();
        assert !(movie.getYear() == 0); // No movie is made in year 0 (from my knowledge at least)

        // and some more
        assert (Objects.equals(movie.getImdbID(), "tt1745960"));
        assert (Objects.equals(movie.getTitle(), "Top Gun: Maverick"));
        assert (Objects.equals(movie.getSummary(), "After thirty years, Maverick is still pushing the envelope as a top naval aviator, but must confront ghosts of his past when he leads TOP GUN's elite graduates on a mission that demands the ultimate sacrifice from those chosen to ..."));
        assert (Objects.equals(movie.getGenres(), "Action, Drama"));
        // Can't check ratings, they might change over time
        assert (!movie.getImdbScore().isEmpty());
        assert (!movie.getRottenTomatoesScore().isEmpty());
        assert (!movie.getMetacriticScore().isEmpty());
        assert (Objects.equals(movie.getDirector(), "Joseph Kosinski"));
        assert (Objects.equals(movie.getActors(), "Tom Cruise, Jennifer Connelly, Miles Teller"));
        assert (Objects.equals(movie.getActors(), "Tom Cruise, Jennifer Connelly, Miles Teller"));
        assert (Objects.equals(movie.getPosterURL(), "https://m.media-amazon.com/images/M/MV5BZWYzOGEwNTgtNWU3NS00ZTQ0LWJkODUtMmVhMjIwMjA1ZmQwXkEyXkFqcGdeQXVyMjkwOTAyMDU@._V1_SX300.jpg"));
        assert (movie.getYear() == 2022);
        assert (Objects.equals(movie.getRuntime(), 130)); // comes in minutes
    }
}
