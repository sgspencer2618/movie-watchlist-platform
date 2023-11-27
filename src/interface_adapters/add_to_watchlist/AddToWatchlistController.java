package interface_adapters.add_to_watchlist;

import entity.Movie;
import use_case.add_to_watchlist.AddToWatchlistInputBoundary;
import use_case.add_to_watchlist.AddToWatchlistInputData;

public class AddToWatchlistController {

    final AddToWatchlistInputBoundary addToWatchListUseCaseInteractor;

    public AddToWatchlistController(AddToWatchlistInputBoundary addToWatchListUseCaseInteractor) {
        this.addToWatchListUseCaseInteractor = addToWatchListUseCaseInteractor;
    }

    public void execute(Movie movie, String user) {
        AddToWatchlistInputData addToWatchlistInputData = new AddToWatchlistInputData(movie, user);
        addToWatchListUseCaseInteractor.execute(addToWatchlistInputData);
    }
}
