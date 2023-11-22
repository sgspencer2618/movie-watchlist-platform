package use_case.add_to_watchlist;
import entity.Watchlist;
import java.util.List;

public class AddToWatchlistInteractor implements AddToWatchlistInputBoundary {
    private final AddToWatchlistDataAccessInterface watchlistAccessObject;
    private final AddToWatchlistOutputBoundary addToWatchListPresenter;

    public AddToWatchlistInteractor(AddToWatchlistDataAccessInterface watchlistAccessObject, AddToWatchlistOutputBoundary addToWatchListPresenter) {
        this.watchlistAccessObject = watchlistAccessObject;
        this.addToWatchListPresenter = addToWatchListPresenter;
    }

    @Override
    public void execute(AddToWatchlistInputData addToWatchlistInputData) {
        Watchlist watchlist;
        try {
            watchlist = watchlistAccessObject.getWatchlist(addToWatchlistInputData.getUser());
        } catch (NullPointerException e1) {
            addToWatchListPresenter.prepareFailView("Watchlist or Movie does not exist");
            return;
        }
        List<String> movieIDs = watchlist.getMovieIDs();
        movieIDs.add(addToWatchlistInputData.getMovie().getImdbID());
        watchlist.setMovieIDs(movieIDs);
        AddToWatchlistOutputData addToWatchlistOutputData = new AddToWatchlistOutputData(
            addToWatchlistInputData.getMovie());
        addToWatchListPresenter.prepareSuccessView(addToWatchlistOutputData);
    }
}
