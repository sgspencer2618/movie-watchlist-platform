package data_access;

// Entities
import entity.Watchlist;
import use_case.add_to_watchlist.AddToWatchlistDataAccessInterface;
import use_case.remove_from_watchlist.RemoveFromWatchlistDataAccessInterface;

public class WatchlistAccessObject implements AddToWatchlistDataAccessInterface, RemoveFromWatchlistDataAccessInterface {
    @Override
    public Watchlist getWatchlist(String user) {
        // TODO: we have to implement the database first, for now just returns a new empty watchlist.
        return  new Watchlist(0, user);
    }
}