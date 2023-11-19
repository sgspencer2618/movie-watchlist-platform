package interface_adapters.remove_from_watchlist;

import entity.Movie;
import use_case.remove_from_watchlist.RemoveFromWatchlistInputBoundary;
import use_case.remove_from_watchlist.RemoveFromWatchlistInputData;

public class RemoveFromWatchlistController {

    final RemoveFromWatchlistInputBoundary RemoveFromWatchlistUseCaseInteractor;

    public RemoveFromWatchlistController(RemoveFromWatchlistInputBoundary RemoveFromWatchlistUseCaseInteractor) {
        this.RemoveFromWatchlistUseCaseInteractor = RemoveFromWatchlistUseCaseInteractor;
    }

    public void execute(Movie movie, String user) {
        RemoveFromWatchlistInputData RemoveFromWatchlistInputData = new RemoveFromWatchlistInputData(movie, user);
        RemoveFromWatchlistUseCaseInteractor.execute(RemoveFromWatchlistInputData);
    }
}
