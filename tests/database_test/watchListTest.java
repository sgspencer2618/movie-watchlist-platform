package database_test;
import java.util.List;

import org.junit.Test;

import data_access.UserAccessObject;
import data_access.WatchlistAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import entity.Watchlist;

public class watchListTest {
    WatchlistAccessObject watchlistAccess = new WatchlistAccessObject("./testWatchlist.csv");
    UserAccessObject userAccess = new UserAccessObject();
    UserFactory userFac = new CommonUserFactory();
    int user_count = 0;

    @Test
    public void addToWatchlistTest() {
        User user = userFac.create(String.format("test%d", user_count), "badPass");
        boolean suc = watchlistAccess.addToWatchlist(user.getUsername(), "Don't look");
        user_count++;
        assert suc;

        // now one user watchlist exists
        watchlistAccess.addToWatchlist(user.getUsername(), "NEW ITEM");
        Watchlist watchlist = watchlistAccess.getWatchlist(user.getUsername());
        assert watchlist.getMovieIDs().get(0).equals("Don't look");
        assert watchlist.getMovieIDs().get(1).equals("NEW ITEM");
    }

    @Test
    public void getWatchlistTest() {
        this.addToWatchlistTest();
        assert(user_count > 0);
        String username = String.format("test%d", user_count-1);
        Watchlist watchlist = watchlistAccess.getWatchlist(username);
        assert watchlist.getUserName().equals(username);
    }

    @Test
    public void removeWatchListTest() {
        this.addToWatchlistTest();
        String username = String.format("test%d", user_count-1);
        assert (watchlistAccess.removeFromWatchlist(username, "CLEARLY NOT A MOVIE") == false);
        assert watchlistAccess.removeFromWatchlist(username, "NEW ITEM");
        Watchlist watchlist = watchlistAccess.getWatchlist(username);
        List<String> movieIDS = watchlist.getMovieIDs();
        assert (movieIDS.contains("NEW ITEM") == false);
    }
}
