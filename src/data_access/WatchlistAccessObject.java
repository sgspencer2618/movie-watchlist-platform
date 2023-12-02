package data_access;

// Entities
import entity.Watchlist;
import use_case.add_to_watchlist.AddToWatchlistDataAccessInterface;
import use_case.get_watchlist.GetWatchlistDataAccessInterface;
import use_case.remove_from_watchlist.RemoveFromWatchlistDataAccessInterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class WatchlistAccessObject implements GetWatchlistDataAccessInterface, AddToWatchlistDataAccessInterface, RemoveFromWatchlistDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final HashMap<String, Watchlist> watchlistMap = new HashMap<>();

    public WatchlistAccessObject(String csvPath) {
        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("movieID", 1);

        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                assert header.equals("username,movieID");
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String movieID = String.valueOf(col[headers.get("movieID")]);
                    Watchlist entry = watchlistMap.get(username);
                    if (entry != null) {
                        List<String> movieIDs = entry.getMovieIDs();
                        movieIDs.add(movieID);
                    } else {
                        entry = new Watchlist(username);
                        List<String> movieIDS = new ArrayList<>();
                        movieIDS.add(movieID);
                        entry.setMovieIDs(movieIDS);
                        watchlistMap.put(username, entry);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Watchlist getWatchlist(String user) {
        Watchlist watch = watchlistMap.get(user);
        if (watch == null) {
            watch = new Watchlist(user);
            List<String> movieIDs = new ArrayList<>();
            watch.setMovieIDs(movieIDs);
            watchlistMap.put(user, watch);
        }
        return watch;
    }

    @Override
    public boolean addToWatchlist(String username, String imdbID) {
        if (watchlistMap.containsKey(username)) {
            Watchlist entry = watchlistMap.get(username);
            List<String> movieIDs = entry.getMovieIDs();
            if (movieIDs.contains(imdbID)) {
                return false;
            }
            movieIDs.add(imdbID);
        } else {
            Watchlist entry = new Watchlist(username);
            List<String> movieIDs = new ArrayList<>();
            movieIDs.add(imdbID);
            entry.setMovieIDs(movieIDs);
            watchlistMap.put(username, entry);
        }
        this.save();
        return true;
    }

    @Override
    public boolean removeFromWatchlist(String username, String imdbID) {
        boolean found = false;
        if (watchlistMap.containsKey(username)) {
            Watchlist entry = watchlistMap.get(username);
            List<String> movieIDs = entry.getMovieIDs();
            ListIterator<String> itr = movieIDs.listIterator();
            while (itr.hasNext()) {
                String id = itr.next();
                if (id.equals(imdbID)) {
                    itr.remove();
                    found = true;
                }
            }
        }
        this.save();
        return found;
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Watchlist watchList : watchlistMap.values()) {
                for (String movieID : watchList.getMovieIDs()) {
                String line = String.format("%s,%s",
                        watchList.getUserName(), movieID);
                writer.write(line);
                writer.newLine();
                }
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}