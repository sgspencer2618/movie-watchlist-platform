package use_case.remove_from_watchlist;
import entity.Watchlist;
import java.util.List;

public class RemoveFromWatchlistInteractor implements RemoveFromWatchlistInputBoundary {
    private final RemoveFromWatchlistDataAccessInterface watchlistAccessObject;
    private final RemoveFromWatchlistOutputBoundary RemoveFromWatchlistPresenter;

    public RemoveFromWatchlistInteractor(RemoveFromWatchlistDataAccessInterface watchlistAccessObject, RemoveFromWatchlistOutputBoundary RemoveFromWatchlistPresenter) {
        this.watchlistAccessObject = watchlistAccessObject;
        this.RemoveFromWatchlistPresenter = RemoveFromWatchlistPresenter;
    }

    @Override
    public void execute(RemoveFromWatchlistInputData RemoveFromWatchlistInputData) {
        Watchlist watchlist;
        try {
            watchlist = watchlistAccessObject.getWatchlist(RemoveFromWatchlistInputData.getUser());
        } catch (NullPointerException e1) {
            RemoveFromWatchlistPresenter.prepareFailView("Watchlist or Movie does not exist");
            return;
        }
        List<String> movieIDs = watchlist.getMovieIDs();
        movieIDs.add(RemoveFromWatchlistInputData.getMovie().getImdbID());
        watchlist.setMovieIDs(movieIDs);
        RemoveFromWatchlistOutputData addToWatchlistOutputData = new RemoveFromWatchlistOutputData(
            RemoveFromWatchlistInputData.getMovie());
            RemoveFromWatchlistPresenter.prepareSuccessView(addToWatchlistOutputData);
    }
}
