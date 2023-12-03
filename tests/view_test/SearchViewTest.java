package view_test;

import app.*;
import data_access.UserRatingAccessObject;
import data_access.WatchlistAccessObject;
import interface_adapters.add_to_watchlist.AddToWatchlistController;
import interface_adapters.movie_info.MovieInfoViewModel;
import interface_adapters.remove_from_watchlist.RemoveFromWatchlistController;
import interface_adapters.remove_rating.RemoveRatingController;
import interface_adapters.remove_rating.RemoveRatingViewModel;
import interface_adapters.search.SearchViewModel;
import interface_adapters.update_rating.UpdateRatingController;
import interface_adapters.update_rating.UpdateRatingViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utility.ApiInterface;
import utility.OMDBCaller;
import view.*;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class SearchViewTest {

    private final String watchlistFilePath = "./testWatchlist.csv";
    private final String ratingsFilePath = "./testUserRating.csv";
    private List<String> originalWatchlistContent, originalRatingsContent;

    @Before
    public void setUp() throws IOException {
        // Save the original file content
        originalWatchlistContent = Files.readAllLines(Paths.get(watchlistFilePath));
        originalRatingsContent = Files.readAllLines(Paths.get(ratingsFilePath));
    }

    @Test
    public void testSearchView() {
        ApiInterface apiCaller = new OMDBCaller();
        UserRatingAccessObject ratingsAccessObject = new UserRatingAccessObject(ratingsFilePath);
        WatchlistAccessObject watchlistAccessObject = new WatchlistAccessObject(watchlistFilePath);

        SearchViewModel searchViewModel = new SearchViewModel();

        MovieInfoViewModel movieInfoViewModel = new MovieInfoViewModel();
        MovieInfoView movieInfoView = MovieInfoUseCaseFactory.create(apiCaller, movieInfoViewModel);

        AddToWatchlistController addToWatchlistController = AddToWatchlistUseCaseFactory.createAddToWatchlistUseCase(watchlistAccessObject);

        RemoveFromWatchlistController removeFromWatchlistController = RemoveFromWatchlistUseCaseFactory.createRemoveFromWatchlistUseCase(watchlistAccessObject);

        UpdateRatingViewModel updateRatingViewModel = new UpdateRatingViewModel();
        UpdateRatingController updateRatingController = UpdateRatingUseCaseFactory.createUpdateRatingUseCase(updateRatingViewModel, ratingsAccessObject);

        RemoveRatingViewModel removeRatingViewModel = new RemoveRatingViewModel();
        RemoveRatingController removeRatingController = RemoveRatingUseCaseFactory.createRemoveRatingUseCase(removeRatingViewModel, ratingsAccessObject);

        SearchView searchView = SearchUseCaseFactory.create(apiCaller, searchViewModel, ratingsAccessObject, watchlistAccessObject, movieInfoView, addToWatchlistController, removeFromWatchlistController, updateRatingController, removeRatingController);

        JFrame testFrame = new JFrame("testRatingsView");
        testFrame.setMinimumSize(new Dimension(600,500));

        searchView.setPreferredSize(new Dimension(600, 500));

        testFrame.add(searchView);
        testFrame.setVisible(true);

        // wait for view to be safe
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // test for search button
        JPanel searchInfo = (JPanel) searchView.getComponent(0);
        JPanel buttonPanel = (JPanel) searchInfo.getComponent(2);
        JButton searchButton = (JButton) buttonPanel.getComponent(0);

        // check it
        assertEquals("SEARCH", searchButton.getText());
    }

    @After
    public void tearDown() throws IOException {
        // Restore the original file content
        Files.write(Paths.get(watchlistFilePath), originalWatchlistContent);
        Files.write(Paths.get(ratingsFilePath), originalRatingsContent);
    }
}
