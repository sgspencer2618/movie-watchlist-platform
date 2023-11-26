package data_access;

// Entities
import entity.Watchlist;
import use_case.add_to_watchlist.AddToWatchlistDataAccessInterface;
import use_case.get_watchlist.GetWatchlistDataAccessInterface;
import use_case.remove_from_watchlist.RemoveFromWatchlistDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

public class WatchlistAccessObject implements GetWatchlistDataAccessInterface, AddToWatchlistDataAccessInterface, RemoveFromWatchlistDataAccessInterface {
    @Override
    public Watchlist getWatchlist(String user) {
        // TODO: we have to implement the database first, for now just returns a new test watchlist.
        Watchlist testWatchlist = new Watchlist(0, user);
        List<String> Idlist = new ArrayList<>();
        Idlist.add("tt1229238");
        Idlist.add("tt2935510");
        Idlist.add("tt2911666");
        Idlist.add("tt0468569");
        testWatchlist.setMovieIDs(Idlist);
        return testWatchlist;
    }

    @Override
    public boolean addToWatchlist(String username, String imdbID) {
        // TODO: implement the database such that we can add an element
        return true;
    }

    @Override
    public boolean removeFromWatchlist(String username, String imdbID) {
        // TODO: implement the database such that we can remove an element
        return true;
    }
}