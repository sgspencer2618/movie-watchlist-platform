package view_test;

import app.*;
import data_access.UserRatingAccessObject;
import data_access.WatchlistAccessObject;
import entity.Movie;
import interface_adapters.add_to_watchlist.AddToWatchlistController;
import interface_adapters.get_watchlist.GetWatchlistViewModel;
import interface_adapters.movie_info.MovieInfoViewModel;
import interface_adapters.remove_from_watchlist.RemoveFromWatchlistController;
import interface_adapters.remove_rating.RemoveRatingController;
import interface_adapters.remove_rating.RemoveRatingViewModel;
import interface_adapters.update_rating.UpdateRatingController;
import interface_adapters.update_rating.UpdateRatingViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utility.ApiInterface;
import utility.OMDBCaller;
import view.MovieInfoView;
import view.WatchlistView;

import javax.swing.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.awt.Dimension;

import static org.junit.Assert.*;

public class WatchlistViewTest {

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
    public void testWatchlistView() {
        ApiInterface apiCaller = new OMDBCaller();
        UserRatingAccessObject ratingsAccessObject = new UserRatingAccessObject(ratingsFilePath);
        WatchlistAccessObject watchlistAccessObject = new WatchlistAccessObject(watchlistFilePath);

        GetWatchlistViewModel getWatchlistViewModel = new GetWatchlistViewModel();

        MovieInfoViewModel movieInfoViewModel = new MovieInfoViewModel();
        MovieInfoView movieInfoView = MovieInfoUseCaseFactory.create(apiCaller, movieInfoViewModel);

        AddToWatchlistController addToWatchlistController = AddToWatchlistUseCaseFactory.createAddToWatchlistUseCase(watchlistAccessObject);

        RemoveFromWatchlistController removeFromWatchlistController = RemoveFromWatchlistUseCaseFactory.createRemoveFromWatchlistUseCase(watchlistAccessObject);

        UpdateRatingViewModel updateRatingViewModel = new UpdateRatingViewModel();
        UpdateRatingController updateRatingController = UpdateRatingUseCaseFactory.createUpdateRatingUseCase(updateRatingViewModel, ratingsAccessObject);

        RemoveRatingViewModel removeRatingViewModel = new RemoveRatingViewModel();
        RemoveRatingController removeRatingController = RemoveRatingUseCaseFactory.createRemoveRatingUseCase(removeRatingViewModel, ratingsAccessObject);

        WatchlistView watchlistView = GetWatchlistUseCaseFactory.create(apiCaller, getWatchlistViewModel, watchlistAccessObject, ratingsAccessObject, movieInfoView, addToWatchlistController, removeFromWatchlistController, updateRatingController, removeRatingController);

        JFrame testFrame = new JFrame("testWatchlistView");
        testFrame.setMinimumSize(new Dimension(600,500));

        watchlistView.setPreferredSize(new Dimension(600, 500));

        // add test data for watchlist
        addToWatchlistController.execute(apiCaller.getMovie("tt3896198"), "watchlistViewTestUser");

        // show UI
        watchlistView.showWatchlist("watchlistViewTestUser");
        watchlistView.createWatchlistPanel();

        testFrame.add(watchlistView);
        testFrame.setVisible(true);

        List<String> dataList = watchlistAccessObject.getWatchlist("watchlistViewTestUser").getMovieIDs();
        List<Movie> viewList = watchlistView.viewModel.getState().getMovieList();

        // wait for view to be safe
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(dataList.size(), viewList.size());
        for(int i = 0; i < dataList.size(); i++) {
            assertEquals(dataList.get(i), viewList.get(i).getImdbID());
        }
    }

    @After
    public void tearDown() throws IOException {
        // Restore the original file content
        Files.write(Paths.get(watchlistFilePath), originalWatchlistContent);
        Files.write(Paths.get(ratingsFilePath), originalRatingsContent);
    }
}
