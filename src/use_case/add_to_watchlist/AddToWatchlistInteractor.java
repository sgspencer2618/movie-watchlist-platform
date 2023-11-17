package use_case.add_to_watchlist;

import entity.Movie;
import entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddToWatchlistInteractor implements AddToWatchlistInputBoundary {
    private final AddToWatchlistDataAccessInterface watchlistAccessObject;
    private final AddToWatchlistOutputBoundary addToWatchListPresenter;

    public AddToWatchlistInteractor(AddToWatchlistDataAccessInterface watchlistAccessObject, AddToWatchlistOutputBoundary addToWatchListPresenter) {
        this.watchlistAccessObject = watchlistAccessObject;
        this.addToWatchListPresenter = addToWatchListPresenter;
    }

    @Override
    public void execute(AddToWatchlistInputData addToWatchlistInputData) {
        List<Movie> watchlist = watchlistAccessObject.getWatchlist("testUser");
        boolean added = false;

        try {
            added = watchlist.add(addToWatchlistInputData.getMovie());
        } catch (NullPointerException e1) {
            addToWatchListPresenter.prepareFailView("Watchlist or Movie does not exist");
        } catch (IllegalArgumentException e2) {
            addToWatchListPresenter.prepareFailView("Invalid Movie");
        }

        AddToWatchlistOutputData addToWatchlistOutputData = new AddToWatchlistOutputData(
                false, addToWatchlistInputData.getMovie());

        if (added == true) {
            addToWatchListPresenter.prepareSuccessView(addToWatchlistOutputData);
        }
        else {
            addToWatchListPresenter.prepareFailView("Failed to add movie to watchlist.");
        }
    }
}
