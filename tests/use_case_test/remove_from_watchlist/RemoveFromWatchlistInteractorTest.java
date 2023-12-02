package use_case_test.remove_from_watchlist;

import data_access.WatchlistAccessObject;
import entity.Movie;
import entity.Watchlist;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import use_case.remove_from_watchlist.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class RemoveFromWatchlistInteractorTest {
    private final String testFilePath = "./testWatchlist.csv";
    private List<String> originalFileContent;

    @Before
    public void setUp() throws IOException {
        // Save the original file content
        originalFileContent = Files.readAllLines(Paths.get(testFilePath));
    }

    @Test
    public void successTest() {
        Movie testMovie = new Movie("tt1001526", "Megamind", "https://m.media-amazon.com/images/M/MV5BMTAzMzI0NTMzNDBeQTJeQWpwZ15BbWU3MDM3NTAyOTM@._V1_SX300.jpg",
                2010);
        RemoveFromWatchlistInputData inputData = new RemoveFromWatchlistInputData(testMovie, "testUser");

        WatchlistAccessObject removeFromWatchlistDAO = new WatchlistAccessObject(testFilePath);

        RemoveFromWatchlistOutputBoundary successPresenter = new RemoveFromWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(RemoveFromWatchlistOutputData movie) {
                    // Test that the output data is correct
                    assertEquals("Megamind", movie.getMovie().getTitle());
                    assertEquals("tt1001526", movie.getMovie().getImdbID());
                    assertEquals("https://m.media-amazon.com/images/M/MV5BMTAzMzI0NTMzNDBeQTJeQWpwZ15BbWU3MDM3NTAyOTM@" +
                            "._V1_SX300.jpg", movie.getMovie().getPosterURL());

                    // Test that the movie was removed from the watchlist
                    Watchlist watchlist = removeFromWatchlistDAO.getWatchlist("testUser");
                    assertFalse(watchlist.getMovieIDs().contains("tt1001526"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected");
            }
        };

        RemoveFromWatchlistInputBoundary interactor = new RemoveFromWatchlistInteractor(removeFromWatchlistDAO, successPresenter);
        interactor.execute(inputData); // Will send output data to successPresenter to be checked

    }

    @Test
    public void failureTest(){
        Movie testMovie = new Movie("tt1001526", "Megamind", "https://m.media-amazon.com/images/M/MV5BMTAzMzI0NTMzNDBeQTJeQWpwZ15BbWU3MDM3NTAyOTM" +
                "._V1_SX300.jpg", 2010);
        RemoveFromWatchlistInputData inputData = new RemoveFromWatchlistInputData(testMovie, "testUser");

        WatchlistAccessObject removeFromWatchlistDAO = new WatchlistAccessObject(testFilePath);

        RemoveFromWatchlistOutputBoundary failPresenter = new RemoveFromWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(RemoveFromWatchlistOutputData movie) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Unable to remove Megamind from the watchlist", error);
            }
        };

        removeFromWatchlistDAO.removeFromWatchlist("testUser", testMovie.getImdbID());

        RemoveFromWatchlistInputBoundary interactor = new RemoveFromWatchlistInteractor(removeFromWatchlistDAO, failPresenter);
        interactor.execute(inputData); // Will send output data to failPresenter to be checked
    }

    @After
    public void tearDown() throws IOException {
        // Restore the original file content
        Files.write(Paths.get(testFilePath), originalFileContent);
    }
}
