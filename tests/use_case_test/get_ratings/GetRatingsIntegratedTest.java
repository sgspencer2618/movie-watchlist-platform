package use_case_test.get_ratings;

import data_access.UserRatingAccessObject;
import data_access.WatchlistAccessObject;
import entity.UserRating;
import interface_adapters.get_ratings.GetRatingsController;
import interface_adapters.get_ratings.GetRatingsPresenter;
import interface_adapters.get_ratings.GetRatingsState;
import interface_adapters.get_ratings.GetRatingsViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import use_case.get_ratings.*;
import use_case.get_watchlist.GetWatchlistDataAccessInterface;
import utility.ApiInterface;
import utility.OMDBCaller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GetRatingsIntegratedTest {
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
    public void testGetRatings() {
        GetRatingsState state = getRatingsState();

        assertEquals(3, state.getRatings().size());

        List<UserRating> ratings = state.getRatings();
        List<String> movieIDs = new ArrayList<>();
        movieIDs.add("tt2911666");
        movieIDs.add("tt1201607");
        movieIDs.add("tt0295297");
        for ( String movieID : movieIDs) {
            boolean containsTarget = ratings.stream().anyMatch(rating -> rating.getMovieId().equals(movieID));
            assertTrue(containsTarget);
        }

    }

    private static GetRatingsState getRatingsState() {
        GetRatingsDataAccessInterface getRatingsDAO = new UserRatingAccessObject(testRatingsFilePath);
        GetWatchlistDataAccessInterface getWatchlistDAO = new WatchlistAccessObject(testWatchlistFilePath);
        ApiInterface apiCaller = new OMDBCaller();

        GetRatingsViewModel getRatingsViewModel = new GetRatingsViewModel();
        GetRatingsOutputBoundary getRatingsPresenter = new GetRatingsPresenter(getRatingsViewModel);

        GetRatingsInputBoundary getRatingsInteractor = new GetRatingsInteractor(getRatingsDAO, getWatchlistDAO, getRatingsPresenter, apiCaller);
        GetRatingsController getRatingsController = new GetRatingsController(getRatingsInteractor);

        getRatingsController.execute("testUser");
        return getRatingsViewModel.getState();


    }

    @After
    public void tearDown() throws Exception {
        Files.write(Paths.get(testRatingsFilePath), originalRatingsFileContent);
        Files.write(Paths.get(testWatchlistFilePath), originalWatchlistFileContent);
    }
}
