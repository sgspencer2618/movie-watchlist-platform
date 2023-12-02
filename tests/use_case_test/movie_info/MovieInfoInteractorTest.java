package use_case_test.movie_info;

import entity.Movie;
import org.junit.Test;
import use_case.movie_info.*;
import utility.ApiInterface;
import utility.OMDBCaller;

import static org.junit.Assert.*;

public class MovieInfoInteractorTest {

    @Test
    public void testMovie() {
        String imdb = "tt0345950";

        ApiInterface apiCaller = new OMDBCaller();
        MovieInfoOutputBoundary successPresenter = new MovieInfoOutputBoundary() {
            @Override
            public void prepareMovieInfoView(MovieInfoOutputData movieInfoOutputData) {
                Movie m = movieInfoOutputData.getMovieInfo();

                assertNotNull(m);
                assertEquals("The SpongeBob SquarePants Movie", m.getTitle());
                assertEquals(Integer.valueOf(2004), m.getYear());
                assertEquals("After King Neptune's crown is stolen, SpongeBob and Patrick go on a quest in " +
                        "6 days to retrieve his crown. On the way SpongeBob and Patrick defeat many evildoers using " +
                        "their brains and bronzes. While this is happening someone is taking over Bikini Bottom and " +
                        "SpongeBob and Patrick must defeat this mastermind.", m.getSummary());
                assertEquals(Integer.valueOf(87), m.getRuntime());
                assertEquals("Tom Kenny, Jeffrey Tambor, Rodger Bumpass", m.getActors());
                assertEquals("Stephen Hillenburg, Mark Osborne", m.getDirector());
                assertEquals("Animation, Adventure, Comedy", m.getGenres());
                assertEquals("PG", m.getContentRating());
                assertEquals("7.1/10", m.getImdbScore());
                assertEquals("68%", m.getRottenTomatoesScore());
                assertEquals("66/100", m.getMetacriticScore());
                assertEquals("https://m.media-amazon.com/images/M/MV5BZjM5YjI0NmQtOTk4OS00NTNiLThkNzQtNT" +
                        "ZlNGE4Y2VmNmU3XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg", m.getPosterURL());
            }
        };

        MovieInfoInputData inputData = new MovieInfoInputData(imdb);
        MovieInfoInputBoundary interactor = new MovieInfoInteractor(apiCaller, successPresenter);
        interactor.execute(inputData); // Will send output data to successPresenter to be checked
    }


    @Test
    public void testLowDataMovie() {
        // test a movie that has a lot of missing data.
        String imdb = "tt25541890";

        ApiInterface apiCaller = new OMDBCaller();
        MovieInfoOutputBoundary successPresenter = new MovieInfoOutputBoundary() {
            @Override
            public void prepareMovieInfoView(MovieInfoOutputData movieInfoOutputData) {
                Movie m = movieInfoOutputData.getMovieInfo();

                assertNotNull(m);
                assertEquals("The Basic Movie", m.getTitle());
                assertEquals(Integer.valueOf(2022), m.getYear());
                assertEquals("N/A", m.getSummary());
                assertNull(m.getRuntime());
                assertEquals("Animation, Adventure, Comedy", m.getGenres());
                assertEquals("N/A", m.getContentRating());
                assertEquals("N/A", m.getImdbScore());
                assertEquals("N/A", m.getRottenTomatoesScore());
                assertEquals("N/A", m.getMetacriticScore());
                assertEquals("https://m.media-amazon.com/images/M/MV5BZWRjMTUzODQtM2NhNC00MDAxLWFmNjItMGMwMWJkMGViYTZhXkEyXkFqcGdeQXVyMTQ5MzI3OTMy._V1_SX300.jpg", m.getPosterURL());
            }
        };

        MovieInfoInputData inputData = new MovieInfoInputData(imdb);
        MovieInfoInputBoundary interactor = new MovieInfoInteractor(apiCaller, successPresenter);
        interactor.execute(inputData); // Will send output data to successPresenter to be checked
    }
}
