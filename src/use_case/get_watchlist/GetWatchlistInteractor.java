package use_case.get_watchlist;

import entity.Movie;

import java.util.List;

public class GetWatchlistInteractor implements GetWatchlistInputBoundary {

    private final GetWatchlistDataAccessInterface watchlistAccessObject;

    private final GetWatchlistOutputBoundary getWatchListPresenter;


    public GetWatchlistInteractor(GetWatchlistDataAccessInterface watchlistAccessObject, GetWatchlistOutputBoundary getWatchListPresenter) {
        this.watchlistAccessObject = watchlistAccessObject;
        this.getWatchListPresenter = getWatchListPresenter;
    }

    @Override
    public void execute(GetWatchlistInputData getWatchlistInputData) {
        String currUser = getWatchlistInputData.getCurrUsername();

        List<Movie> watchlist = watchlistAccessObject.getWatchlist(currUser);
        Boolean useCaseFailed = false;

        GetWatchlistOutputData outputData = new GetWatchlistOutputData(useCaseFailed, watchlist);
        getWatchListPresenter.prepareSuccessView(outputData);
    }

}
