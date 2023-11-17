package use_case.get_watchlist;

import entity.Movie;

import java.util.List;

public class GetWatchlistOutputData {
    private final boolean useCaseFailed;
    private final List<Movie> watchlist;

    public GetWatchlistOutputData(boolean useCaseFailed, List<Movie> watchlist) {
        this.useCaseFailed = useCaseFailed;
        this.watchlist = watchlist;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public List<Movie> getWatchlist() {
        return watchlist;
    }
}
