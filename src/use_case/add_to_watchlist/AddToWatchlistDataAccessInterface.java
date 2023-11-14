package use_case.add_to_watchlist;

import entity.Movie;
import java.util.List;

public interface AddToWatchlistDataAccessInterface {
    public List<Movie> getWatchlist(List watchlist);
}
