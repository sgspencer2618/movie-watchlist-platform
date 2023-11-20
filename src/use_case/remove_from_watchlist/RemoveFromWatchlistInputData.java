package use_case.remove_from_watchlist;

import entity.Movie;

public class RemoveFromWatchlistInputData {
    private final Movie movie;
    private final String user;

    public RemoveFromWatchlistInputData(Movie movie, String user) {
        this.movie = movie;
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getUser() {
        return user;
    }
}
