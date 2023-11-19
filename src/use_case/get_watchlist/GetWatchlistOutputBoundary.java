package use_case.get_watchlist;

public interface GetWatchlistOutputBoundary {
    void prepareSuccessView(GetWatchlistOutputData getWatchlistOutputData);

    void prepareFailView(String error);
}
