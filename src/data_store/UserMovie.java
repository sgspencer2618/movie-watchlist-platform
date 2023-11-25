package data_store;

import java.io.Serializable;

public class UserMovie implements Serializable {
    private final String movieID;

    private boolean inWatchlist;

    private int rating;

    public UserMovie(String movieID){
        this.movieID = movieID;
        this.inWatchlist = false;
        this.rating = 0;
    }

    public String getMovieID(){return movieID;}

    public boolean isInWatchlist(){return inWatchlist;}

    public int getRating(){return rating;}

    public void setInWatchlist(boolean inWatchlist){this.inWatchlist = inWatchlist;}

    public void setRating(int rating){this.rating = rating;}
}
