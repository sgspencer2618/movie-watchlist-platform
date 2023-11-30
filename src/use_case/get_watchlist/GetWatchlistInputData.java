package use_case.get_watchlist;

public class GetWatchlistInputData {

    private final String user;

    public GetWatchlistInputData(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }
}
