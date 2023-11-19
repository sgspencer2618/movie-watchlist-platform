package entity;

import java.util.List;

public class WatchList {
    private final String userName;
    private final List<String> movieIDs;

    private final int id;

    public WatchList(int id, String userName, List<String> movieIDs) {
        this.id = id;
        this.userName = userName;
        this.movieIDs = movieIDs;
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

}