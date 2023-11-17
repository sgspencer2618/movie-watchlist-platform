package use_case.get_watchlist;

import entity.Watchlist;

public class GetWatchlistOutputData {
    private final Watchlist watchlist;

    public GetWatchlistOutputData(Watchlist watchlist) {
        this.watchlist = watchlist;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }
}
