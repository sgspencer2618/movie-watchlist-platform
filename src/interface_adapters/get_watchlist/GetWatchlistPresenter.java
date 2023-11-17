package interface_adapters.get_watchlist;

import use_case.get_watchlist.GetWatchlistOutputBoundary;
import use_case.get_watchlist.GetWatchlistOutputData;

public class GetWatchlistPresenter implements GetWatchlistOutputBoundary {
    @Override
    public void prepareSuccessView(GetWatchlistOutputData getWatchlistOutputData) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
