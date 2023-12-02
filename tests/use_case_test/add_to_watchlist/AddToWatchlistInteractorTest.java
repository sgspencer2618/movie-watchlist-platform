package use_case_test.add_to_watchlist;

import data_access.WatchlistAccessObject;
import entity.Movie;
import entity.Watchlist;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import use_case.add_to_watchlist.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class AddToWatchlistInteractorTest {
    private final String testFilePath = "./testWatchlist.csv";
    private List<String> originalFileContent;

    @Before
    public void setUp() throws IOException {
        // Save the original file content
        originalFileContent = Files.readAllLines(Paths.get(testFilePath));
    }


    @Test
    public void successTest() {
        Movie testMovie = new Movie("tt0126029", "Shrek",
                "https://m.media-amazon.com/images/M/MV5BOGZhM2FhNTItODAzNi00YjA0LWEyN2UtNjJlYWQzYzU1MDg5L2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg",
                2001);
        AddToWatchlistInputData inputData = new AddToWatchlistInputData(testMovie, "testUser");

        WatchlistAccessObject addToWatchlistDAO = new WatchlistAccessObject(testFilePath);

        AddToWatchlistOutputBoundary successPresenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(AddToWatchlistOutputData movie) {

                // Test that the output data is correct
                assertEquals("Shrek", movie.getMovie().getTitle());
                assertEquals("tt0126029", movie.getMovie().getImdbID());
                assertEquals("https://m.media-amazon.com/images/M/MV5BOGZhM2FhNTItODAzNi00YjA0LWEyN2UtNjJlYWQzYzU1MDg5L2lt" +
                        "YWdlL2ltYWdlXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg", movie.getMovie().getPosterURL());

                // Test that the movie was added to the watchlist
                Watchlist watchlist = addToWatchlistDAO.getWatchlist("testUser");
                assertTrue(watchlist.getMovieIDs().contains("tt0126029"));

            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected");
            }
        };

        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(addToWatchlistDAO, successPresenter);
        interactor.execute(inputData);

    }

    @Test
    public void failureTest() {
        Movie testMovie = new Movie("tt0126029", "Shrek",
                "https://m.media-amazon.com/images/M/MV5BOGZhM2FhNTItODAzNi00YjA0LWEyN2UtNjJlYWQzYzU1MDg5L2lt" +
                        "YWdlL2ltYWdlXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg",
                2001);
        AddToWatchlistInputData inputData = new AddToWatchlistInputData(testMovie, "testUser");

        WatchlistAccessObject addToWatchlistDAO = new WatchlistAccessObject(testFilePath);

        AddToWatchlistOutputBoundary failurePresenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(AddToWatchlistOutputData movie) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Unable to add Shrek to the watchlist", error);
            }
        };

        // Add the movie to the watchlist
        addToWatchlistDAO.addToWatchlist("testUser", testMovie.getImdbID());

        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(addToWatchlistDAO, failurePresenter);
        interactor.execute(inputData);

    }

    @After
    public void tearDown() throws IOException {
        // Restore the original file content
        Files.write(Paths.get(testFilePath), originalFileContent);
    }
}
