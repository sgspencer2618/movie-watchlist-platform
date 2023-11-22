package entity;

import java.util.List;

public class Watchlist {
    private final String userName;
    private List<String> movieIDs;

    private final int id;

    public Watchlist(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public int getId () {
        return id;
    }
    public String getUserName () {
        return userName;
    }
    public List<String> getMovieIDs () {
        return movieIDs;
    }
    public void setMovieIDs (List<String> movieIDs) {
        this.movieIDs = movieIDs;
    }
}