package use_case.add_to_watchlist;

public interface AddToWatchlistOutputBoundary {
    void prepareSuccessView(AddToWatchlistOutputData movie);
    void prepareFailView(String error);
}
