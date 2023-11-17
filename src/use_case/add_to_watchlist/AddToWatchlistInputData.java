package use_case.add_to_watchlist;

import entity.Movie;

public class AddToWatchlistInputData {
    private final Movie movie;
    private final String user;

    public AddToWatchlistInputData(Movie movie, String user) {
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
