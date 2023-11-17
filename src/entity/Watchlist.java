package entity;

public class Watchlist {
    private final int userID;
    private final int watchlistID; //independent watchlist ID in case users will be able to create more than one watchlist in the future

    public Watchlist(int userID, int watchlistID) {
        this.userID = userID;
        this.watchlistID = watchlistID;
    }

    public int getUserID() {
        return userID;
    }

    public int getWatchlistID() {
        return watchlistID;
    }
}
