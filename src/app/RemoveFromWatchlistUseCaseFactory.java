package app;
import interface_adapters.remove_from_watchlist.RemoveFromWatchlistController;
import interface_adapters.remove_from_watchlist.RemoveFromWatchlistPresenter;
import use_case.remove_from_watchlist.RemoveFromWatchlistDataAccessInterface;
import use_case.remove_from_watchlist.RemoveFromWatchlistInputBoundary;
import use_case.remove_from_watchlist.RemoveFromWatchlistInteractor;
import use_case.remove_from_watchlist.RemoveFromWatchlistOutputBoundary;

public class RemoveFromWatchlistUseCaseFactory {

    public static RemoveFromWatchlistController createRemoveFromWatchlistUseCase(RemoveFromWatchlistDataAccessInterface removeFromWatchlistDataAccessObject) {
        RemoveFromWatchlistOutputBoundary removeFromWatchlistPresenter = new RemoveFromWatchlistPresenter();
        RemoveFromWatchlistInputBoundary addToWatchlistInteractor = new RemoveFromWatchlistInteractor(removeFromWatchlistDataAccessObject, removeFromWatchlistPresenter);

        return new RemoveFromWatchlistController(addToWatchlistInteractor);
    }
}
