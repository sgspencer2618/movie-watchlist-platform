package use_case.get_watchlist;

public class GetWatchlistInputData {
    private final String currUsername;

    public GetWatchlistInputData(String currUsername) {
        this.currUsername = currUsername;
    }

    public String getCurrUsername() {
        return currUsername;
    }
}
