package interface_adapters.remove_from_watchlist;

import use_case.remove_from_watchlist.RemoveFromWatchlistOutputBoundary;
import use_case.remove_from_watchlist.RemoveFromWatchlistOutputData;

public class RemoveFromWatchlistPresenter implements RemoveFromWatchlistOutputBoundary {

    @Override
    public void prepareFailView(String error) {

    }

    @Override
    public void prepareSuccessView(RemoveFromWatchlistOutputData movie) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'prepareSuccessView'");
    }
}
