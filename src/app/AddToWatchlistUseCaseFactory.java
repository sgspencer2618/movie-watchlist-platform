package app;
import interface_adapters.add_to_watchlist.AddToWatchlistController;
import interface_adapters.add_to_watchlist.AddToWatchlistPresenter;
import use_case.add_to_watchlist.AddToWatchlistDataAccessInterface;
import use_case.add_to_watchlist.AddToWatchlistInputBoundary;
import use_case.add_to_watchlist.AddToWatchlistInteractor;
import use_case.add_to_watchlist.AddToWatchlistOutputBoundary;

public class AddToWatchlistUseCaseFactory {

    public static AddToWatchlistController createAddToWatchlistUseCase(AddToWatchlistDataAccessInterface addToWatchlistAccessObject) {
        AddToWatchlistOutputBoundary addToWatchlistPresenter = new AddToWatchlistPresenter();
        AddToWatchlistInputBoundary addToWatchlistInteractor = new AddToWatchlistInteractor(addToWatchlistAccessObject, addToWatchlistPresenter);

        return new AddToWatchlistController(addToWatchlistInteractor);
    }
}
