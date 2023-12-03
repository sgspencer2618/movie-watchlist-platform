package use_case_test.get_watchlist;

import data_access.UserRatingAccessObject;
import data_access.WatchlistAccessObject;
import entity.Watchlist;
import interface_adapters.get_watchlist.GetWatchlistController;
import interface_adapters.get_watchlist.GetWatchlistPresenter;
import interface_adapters.get_watchlist.GetWatchlistState;
import interface_adapters.get_watchlist.GetWatchlistViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import use_case.get_ratings.GetRatingsDataAccessInterface;
import use_case.get_ratings.GetRatingsInputBoundary;
import use_case.get_watchlist.GetWatchlistDataAccessInterface;
import use_case.get_watchlist.GetWatchlistInputBoundary;
import use_case.get_watchlist.GetWatchlistInteractor;
import use_case.get_watchlist.GetWatchlistOutputBoundary;
import utility.ApiInterface;
import utility.OMDBCaller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetWatchlistIntegratedTest {
    private static final String testRatingsFilePath = "./testUserRating.csv";
    private static final String testWatchlistFilePath = "./testWatchlist.csv";
    private List<String> originalRatingsFileContent;
    private List<String> originalWatchlistFileContent;

    @Before
    public void setUp() throws Exception {
        originalRatingsFileContent = Files.readAllLines(Paths.get(testRatingsFilePath));
        originalWatchlistFileContent = Files.readAllLines(Paths.get(testWatchlistFilePath));
    }

    @Test
    public void testGetWatchlist() {
        GetWatchlistState state = getWatchlistState();

        assertEquals(1, state.getWatchlist().getMovieIDs().size());

        Watchlist watchlist = state.getWatchlist();
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

    private static GetWatchlistState getWatchlistState() {
        GetWatchlistDataAccessInterface getWatchlistDAO = new WatchlistAccessObject(testWatchlistFilePath);
        UserRatingAccessObject getRatingsDAO = new UserRatingAccessObject(testRatingsFilePath);
        ApiInterface apiCaller = new OMDBCaller();

        GetWatchlistViewModel getWatchlistViewModel = new GetWatchlistViewModel();
        GetWatchlistOutputBoundary getWatchlistPresenter = new GetWatchlistPresenter(getWatchlistViewModel);

        GetWatchlistInputBoundary getWatchlistInteractor = new GetWatchlistInteractor(getWatchlistDAO, getRatingsDAO, apiCaller, getWatchlistPresenter);
        GetWatchlistController getWatchlistController = new GetWatchlistController(getWatchlistInteractor);

        getWatchlistController.execute("testUser");
        return getWatchlistViewModel.getState();
    }

    @After
    public void tearDown() throws Exception {
        Files.write(Paths.get(testRatingsFilePath), originalRatingsFileContent);
        Files.write(Paths.get(testWatchlistFilePath), originalWatchlistFileContent);
    }
}
