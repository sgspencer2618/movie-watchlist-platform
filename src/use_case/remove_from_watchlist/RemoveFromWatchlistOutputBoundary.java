package use_case.remove_from_watchlist;

public interface RemoveFromWatchlistOutputBoundary {
    void prepareSuccessView(RemoveFromWatchlistOutputData movie);
    void prepareFailView(String error);
}
