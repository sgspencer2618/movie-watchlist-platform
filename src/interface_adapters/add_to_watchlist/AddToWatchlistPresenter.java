package interface_adapters.add_to_watchlist;

import use_case.add_to_watchlist.AddToWatchlistOutputBoundary;
import use_case.add_to_watchlist.AddToWatchlistOutputData;

import interface_adapters.ViewManagerModel;;

public class AddToWatchlistPresenter implements AddToWatchlistOutputBoundary {
    @Override
    public void prepareSuccessView(AddToWatchlistOutputData failedMovie) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
