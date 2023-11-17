package use_case.add_to_watchlist;

import entity.Movie;

public class AddToWatchlistOutputData {
    private final boolean useCaseFailed;
    private final Movie movie;

    public AddToWatchlistOutputData(boolean useCaseFailed, Movie movie) {
        this.useCaseFailed = useCaseFailed;
        this.movie = movie;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public Movie getMovie() {
        return movie;
    }
}
