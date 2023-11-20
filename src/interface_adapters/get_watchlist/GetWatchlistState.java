package interface_adapters.get_watchlist;

import entity.Watchlist;

public class GetWatchlistState {

    private Watchlist watchlist;
    private String user;

    public GetWatchlistState(GetWatchlistState copy) {
        this.watchlist = copy.watchlist;
        this.user = copy.user;
    }

    GetWatchlistState() {}

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
