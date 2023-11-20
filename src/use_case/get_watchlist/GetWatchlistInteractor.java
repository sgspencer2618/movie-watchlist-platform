package use_case.get_watchlist;

import entity.Movie;
import entity.User;
import entity.Watchlist;

import java.util.List;

public class GetWatchlistInteractor implements GetWatchlistInputBoundary{
    private final GetWatchlistDataAccessInterface getWatchlistDataAccessObject;
    private final GetWatchlistOutputBoundary getWatchlistPresenter;

    public GetWatchlistInteractor(GetWatchlistDataAccessInterface getWatchlistDataAccessObject, GetWatchlistOutputBoundary getWatchlistPresenter) {
        this.getWatchlistDataAccessObject = getWatchlistDataAccessObject;
        this.getWatchlistPresenter = getWatchlistPresenter;
    }

    @Override
    public void execute(GetWatchlistInputData getWatchlistInputData) {
        Watchlist watchlist = getWatchlistDataAccessObject.getWatchlist(getWatchlistInputData.getUser());

        GetWatchlistOutputData getWatchlistOutputData = new GetWatchlistOutputData(watchlist);

        if (watchlist != null) {
            getWatchlistPresenter.prepareGetWatchlistView(getWatchlistOutputData);
        }
    }
}
