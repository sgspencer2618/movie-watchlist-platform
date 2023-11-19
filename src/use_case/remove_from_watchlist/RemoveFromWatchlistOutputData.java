package use_case.remove_from_watchlist;
import entity.Movie;

public class RemoveFromWatchlistOutputData {
    private final Movie movie;

    public RemoveFromWatchlistOutputData(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }
}
