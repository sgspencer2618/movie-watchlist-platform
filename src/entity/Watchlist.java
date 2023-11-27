package entity;

import java.util.List;

public class Watchlist {
    private final String userName;
    private List<String> movieIDs;

    public Watchlist( String userName) {
        this.userName = userName;
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