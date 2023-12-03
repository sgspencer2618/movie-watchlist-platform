package use_case_test.get_watchlist;

import data_access.UserRatingAccessObject;
import data_access.WatchlistAccessObject;
import entity.UserRating;
import entity.Watchlist;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import use_case.get_ratings.*;
import use_case.get_watchlist.*;
import utility.ApiInterface;
import utility.OMDBCaller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetWatchlistInteractorTest {
    private final String testWatchlistFilePath = "./testWatchlist.csv";
    private final String testRatingsFilePath = "./testUserRating.csv";
    private List<String> originalRatingsFileContent;

    private List<String> originalWatchlistFileContent;

    @Before
    public void setUp() throws Exception {
        originalRatingsFileContent = Files.readAllLines(Paths.get(testRatingsFilePath));
        originalWatchlistFileContent = Files.readAllLines(Paths.get(testWatchlistFilePath));
    }

    @Test
    public void successTest() {
        GetWatchlistInputData inputData = new GetWatchlistInputData("testUser");
        UserRatingAccessObject getRatingsDAO = new UserRatingAccessObject(testRatingsFilePath);
        GetWatchlistDataAccessInterface getWatchlistDAO = new WatchlistAccessObject(testWatchlistFilePath);
        ApiInterface apiInterface = new OMDBCaller();

        GetWatchlistOutputBoundary successPresenter = new GetWatchlistOutputBoundary() {
            @Override
            public void prepareGetWatchlistView(GetWatchlistOutputData getWatchlistOutputData) {
                assertEquals(1, getWatchlistOutputData.getWatchlist().getMovieIDs().size());

                Watchlist watchlist = getWatchlistOutputData.getWatchlist();
                List<String> movieIDs = new ArrayList<>();
                movieIDs.add("tt1001526");

                boolean containsTarget = true;

                for (String movieID : movieIDs) {
                    for (String watchlistMovie : watchlist.getMovieIDs()) {
                        if (!(watchlistMovie.equals(movieID))) {
                            containsTarget = false;
                        }
                    }
                    assertTrue(containsTarget);
                }
            }
        };

        GetWatchlistInputBoundary Watchlistinteractor = new GetWatchlistInteractor(getWatchlistDAO, getRatingsDAO, apiInterface, successPresenter);
        Watchlistinteractor.execute(inputData);
    }

    @After
    public void tearDown() throws Exception {
        Files.write(Paths.get(testRatingsFilePath), originalRatingsFileContent);
        Files.write(Paths.get(testWatchlistFilePath), originalWatchlistFileContent);
    }

}

