package entity;

import java.util.List;

public class Watchlist {
    private final int userID;
    private final int watchlistID; //independent watchlist ID in case users will be able to create more than one watchlist in the future
    private final List<Movie> watchlist;

    public Watchlist(int userID, int watchlistID, List<Movie> watchlist) {
        this.userID = userID;
        this.watchlistID = watchlistID;
        this.watchlist = watchlist;
    }

    public int getUserID() {
        return userID;
    }

    public int getWatchlistID() {
        return watchlistID;
    }

    public List<Movie> getWatchlist() {
        return watchlist;
    }
}
