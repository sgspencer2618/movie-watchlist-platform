package use_case.add_to_watchlist;

import entity.Movie;

public class AddToWatchlistInputData {
    private final Movie movie;

    public AddToWatchlistInputData(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }
}
