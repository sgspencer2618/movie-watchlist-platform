package use_case_test.search;

import data_access.UserRatingAccessObject;
import data_access.WatchlistAccessObject;
import interface_adapters.search.SearchPresenter;
import interface_adapters.search.SearchViewModel;
import org.junit.Test;
import use_case.get_watchlist.GetWatchlistOutputBoundary;
import use_case.search.*;
import utility.ApiInterface;
import utility.OMDBCaller;
import view.SearchView;

import static org.junit.Assert.assertEquals;

public class SearchInteractorTest {
    private final String testWatchlistFilePath = "./testWatchlist.csv";
    private final String testRatingsFilePath = "./testUserRating.csv";
    private final String testQuery = "Harry potter";

    @Test
    public void testSearch() {
        SearchHandlerInputData searchHandlerInputData = new SearchHandlerInputData(testQuery, "testUser");

        ApiInterface api = new OMDBCaller();

        UserRatingAccessObject ratingAccessObject = new UserRatingAccessObject(testRatingsFilePath);
        WatchlistAccessObject watchlistAccessObject = new WatchlistAccessObject(testWatchlistFilePath);

        SearchHandlerOutputBoundary searchHandlerPresenter = new SearchHandlerOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchHandlerOutputData result) {
                assertEquals(result.getMovieList().isEmpty(), false);

                assertEquals(result.getWatchlist().getMovieIDs().size(), 1);
                //since only 2 of the 3 rated movies for testUser show up in the search query
                assertEquals(result.getRatings().size(), 2);
            }
        };

        SearchHandlerInputBoundary searchInteractor = new SearchInteractor(api, ratingAccessObject, watchlistAccessObject, searchHandlerPresenter);
        searchInteractor.execute(searchHandlerInputData);
    }
}
