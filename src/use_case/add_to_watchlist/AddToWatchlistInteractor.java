package use_case.add_to_watchlist;

public class AddToWatchlistInteractor implements AddToWatchlistInputBoundary {
    private final AddToWatchlistDataAccessInterface watchlistAccessObject;
    private final AddToWatchlistOutputBoundary addToWatchListPresenter;

    public AddToWatchlistInteractor(AddToWatchlistDataAccessInterface watchlistAccessObject, AddToWatchlistOutputBoundary addToWatchListPresenter) {
        this.watchlistAccessObject = watchlistAccessObject;
        this.addToWatchListPresenter = addToWatchListPresenter;
    }

    @Override
    public void execute(AddToWatchlistInputData addToWatchlistInputData) {
        AddToWatchlistOutputData addToWatchlistOutputData = new AddToWatchlistOutputData(
            addToWatchlistInputData.getMovie());
        if (watchlistAccessObject.addToWatchlist(addToWatchlistInputData.getUser(), addToWatchlistInputData.getMovie().getImdbID())) {
            addToWatchListPresenter.prepareSuccessView(addToWatchlistOutputData);
        } else {
            addToWatchListPresenter.prepareFailView(String.format("Unable to add movie %s to the watchlist", addToWatchlistInputData.getMovie().getTitle()));
        }
    }
}
