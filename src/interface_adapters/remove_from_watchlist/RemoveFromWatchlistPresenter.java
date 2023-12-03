package interface_adapters.remove_from_watchlist;

import use_case.remove_from_watchlist.RemoveFromWatchlistOutputBoundary;
import use_case.remove_from_watchlist.RemoveFromWatchlistOutputData;

public class RemoveFromWatchlistPresenter implements RemoveFromWatchlistOutputBoundary {

    @Override
    public void prepareSuccessView(RemoveFromWatchlistOutputData movie) {
        // Do nothing
    }

    @Override
    public void prepareFailView(String error) {
        System.err.println(error);
    }
}
