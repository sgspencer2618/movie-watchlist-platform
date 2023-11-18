package data_access;

import entity.User;
import entity.Watchlist;
import use_case.add_to_watchlist.AddToWatchlistDataAccessInterface;
import use_case.get_watchlist.GetWatchlistDataAccessInterface;

public class WatchlistAccessObject implements AddToWatchlistDataAccessInterface, GetWatchlistDataAccessInterface {

    @Override
    public Watchlist getWatchlist(String user) {
        return null;
    }
}
