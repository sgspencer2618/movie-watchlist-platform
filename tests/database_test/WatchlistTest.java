package database_test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data_access.WatchlistAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import entity.Watchlist;

public class WatchlistTest {

    private final String testFilePath = "./testWatchlist.csv";
    WatchlistAccessObject watchlistAccess = new WatchlistAccessObject(testFilePath);
    UserFactory userFac = new CommonUserFactory();
    int user_count = 0;

    private List<String> originalFileContent;

    @Before
    public void setUp() throws IOException {
        // Save the original file content
        originalFileContent = Files.readAllLines(Paths.get(testFilePath));
    }

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
        assert (!watchlistAccess.removeFromWatchlist(username, "CLEARLY NOT A MOVIE"));
        assert watchlistAccess.removeFromWatchlist(username, "NEW ITEM");
        assert watchlistAccess.removeFromWatchlist(username, "Don't look");
        Watchlist watchlist = watchlistAccess.getWatchlist(username);
        List<String> movieIDS = watchlist.getMovieIDs();
        assert (!movieIDS.contains("NEW ITEM"));
    }

    @After
    public void tearDown() throws IOException {
        // Restore the original file content
        Files.write(Paths.get(testFilePath), originalFileContent);
    }
}
