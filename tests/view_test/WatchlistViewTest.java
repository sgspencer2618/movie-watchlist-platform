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
import use_case.add_to_watchlist.AddToWatchlistDataAccessInterface;
import use_case.get_watchlist.GetWatchlistDataAccessInterface;
import use_case.remove_from_watchlist.RemoveFromWatchlistDataAccessInterface;
import use_case.remove_rating.RemoveRatingDataAccessInterface;
import use_case.update_rating.UpdateRatingDataAccessInterface;
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

    private List<String> originalFileContent;

    @Before
    public void setUp() throws IOException {
        // Save the original file content
        originalFileContent = Files.readAllLines(Paths.get("./testWatchlist.csv"));
    }

    @Test
    public void testWatchlistView() {
        ApiInterface apiCaller = new OMDBCaller();

        WatchlistAccessObject watchlistAccessObject = new WatchlistAccessObject("./testWatchlist.csv");
        GetWatchlistViewModel getWatchlistViewModel = new GetWatchlistViewModel();

        UserRatingAccessObject ratingAccessObject = new UserRatingAccessObject("./testUserRating.csv");
        MovieInfoViewModel movieInfoViewModel = new MovieInfoViewModel();
        MovieInfoView movieInfoView = MovieInfoUseCaseFactory.create(apiCaller, movieInfoViewModel);

        AddToWatchlistController addToWatchlistController = AddToWatchlistUseCaseFactory.createAddToWatchlistUseCase(watchlistAccessObject);

        RemoveFromWatchlistDataAccessInterface removeFromWatchlistAccessObject = new WatchlistAccessObject("./testWatchlist.csv");
        RemoveFromWatchlistController removeFromWatchlistController = RemoveFromWatchlistUseCaseFactory.createRemoveFromWatchlistUseCase(removeFromWatchlistAccessObject);

        UpdateRatingViewModel updateRatingViewModel = new UpdateRatingViewModel();
        UpdateRatingDataAccessInterface updateRatingAccessObject = new UserRatingAccessObject("./testUserRating.csv");
        UpdateRatingController updateRatingController = UpdateRatingUseCaseFactory.createUpdateRatingUseCase(updateRatingViewModel, updateRatingAccessObject);

        RemoveRatingViewModel removeRatingViewModel = new RemoveRatingViewModel();
        RemoveRatingDataAccessInterface removeRatingAccessObject = new UserRatingAccessObject("./testUserRating.csv");
        RemoveRatingController removeRatingController = RemoveRatingUseCaseFactory.createRemoveRatingUseCase(removeRatingViewModel, removeRatingAccessObject);

        WatchlistView watchlistView = GetWatchlistUseCaseFactory.create(apiCaller, getWatchlistViewModel, watchlistAccessObject, ratingAccessObject, movieInfoView, addToWatchlistController, removeFromWatchlistController, updateRatingController, removeRatingController);

        JFrame testFrame = new JFrame("testWatchlistView");
        testFrame.setMinimumSize(new Dimension(600,500));

        watchlistView.setPreferredSize(new Dimension(600, 500));

        // add test data for watchlist
        addToWatchlistController.execute(apiCaller.getMovie("tt3896198"), "testUser");

        // show UI
        watchlistView.showWatchlist("testUser");
        watchlistView.createWatchlistPanel();

        testFrame.add(watchlistView);
        testFrame.setVisible(true);

        GetWatchlistDataAccessInterface watchlistDataAccessInterface = new WatchlistAccessObject("./testWatchlist.csv");

        List<String> dataList = watchlistDataAccessInterface.getWatchlist("testUser").getMovieIDs();
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
        Files.write(Paths.get("./testWatchlist.csv"), originalFileContent);
    }
}
