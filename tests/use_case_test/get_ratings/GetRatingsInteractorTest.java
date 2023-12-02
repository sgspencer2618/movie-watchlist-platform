package use_case_test.get_ratings;

import data_access.UserRatingAccessObject;
import data_access.WatchlistAccessObject;
import entity.UserRating;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetRatingsInteractorTest {

    private final String testRatingsFilePath = "./testUserRating.csv";
    private final String testWatchlistFilePath = "./testWatchlist.csv";
    private List<String> originalRatingsFileContent;

    private List<String> originalWatchlistFileContent;

    @Before
    public void setUp() throws Exception {
        originalRatingsFileContent = Files.readAllLines(Paths.get(testRatingsFilePath));
        originalWatchlistFileContent = Files.readAllLines(Paths.get(testWatchlistFilePath));
    }

    @Test
    public void successTest() {
        GetRatingsInputData inputData = new GetRatingsInputData("testUser");
        GetRatingsDataAccessInterface getRatingsDAO = new UserRatingAccessObject(testRatingsFilePath);
        GetWatchlistDataAccessInterface getWatchlistDAO = new WatchlistAccessObject(testWatchlistFilePath);
        ApiInterface apiInterface = new OMDBCaller();

        GetRatingsOutputBoundary successPresenter = new GetRatingsOutputBoundary() {
            @Override
            public void prepareGetRatingsView(GetRatingsOutputData getRatingsOutputData) {
                assertEquals(3, getRatingsOutputData.getRatings().size());

                List<UserRating> ratings = getRatingsOutputData.getRatings();
                List<String> movieIDs = new ArrayList<>();
                movieIDs.add("tt2911666");
                movieIDs.add("tt1201607");
                movieIDs.add("tt0295297");
                for ( String movieID : movieIDs) {
                    boolean containsTarget = ratings.stream().anyMatch(rating -> rating.getMovieId().equals(movieID));
                    assertTrue(containsTarget);
                }

            }
        };

        GetRatingsInputBoundary interactor = new GetRatingsInteractor(getRatingsDAO, getWatchlistDAO, successPresenter, apiInterface);
        interactor.execute(inputData);
    }

    @After
    public void tearDown() throws Exception {
        Files.write(Paths.get(testRatingsFilePath), originalRatingsFileContent);
        Files.write(Paths.get(testWatchlistFilePath), originalWatchlistFileContent);
    }

}