package use_case.get_ratings;

import entity.Movie;

import java.util.List;

public class GetRatingsOutputData {
    private final boolean useCaseFailed;
    private final List<Movie> watchlist;

    public GetRatingsOutputData(boolean useCaseFailed, List<Movie> watchlist) {
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
