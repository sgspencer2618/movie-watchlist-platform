package use_case.remove_from_watchlist;


public class RemoveFromWatchlistInteractor implements RemoveFromWatchlistInputBoundary {
    private final RemoveFromWatchlistDataAccessInterface watchlistAccessObject;
    private final RemoveFromWatchlistOutputBoundary removeFromWatchlistPresenter;

    public RemoveFromWatchlistInteractor(RemoveFromWatchlistDataAccessInterface watchlistAccessObject, RemoveFromWatchlistOutputBoundary removeFromWatchlistPresenter) {
        this.watchlistAccessObject = watchlistAccessObject;
        this.removeFromWatchlistPresenter = removeFromWatchlistPresenter;
    }

    @Override
    public void execute(RemoveFromWatchlistInputData removeFromWatchlistInputData) {
        RemoveFromWatchlistOutputData removeFromWatchlistOutputData = new RemoveFromWatchlistOutputData(
            removeFromWatchlistInputData.getMovie());
        if (watchlistAccessObject.removeFromWatchlist(removeFromWatchlistInputData.getUser(), removeFromWatchlistOutputData.getMovie().getImdbID())) {
            removeFromWatchlistPresenter.prepareSuccessView(removeFromWatchlistOutputData);
        }  else {
            removeFromWatchlistPresenter.prepareFailView(String.format("Unable to remove %s from watchlist", removeFromWatchlistInputData.getMovie().getTitle()));
        }
    }
}
