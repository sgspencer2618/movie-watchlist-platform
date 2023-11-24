package data_store;

import java.io.Serializable;

// This class is used to store movie information in the database
public class MovieStore implements Serializable {
    private final String movieID;

    private final String title;

    private final String posterPath;

    private final int year;

    public MovieStore(String movieID, String title, String posterPath, Integer year){
        this.movieID = movieID;
        this.title = title;
        this.posterPath = posterPath;
        this.year = year;
    }

    public String getMovieID(){return movieID;}

    public String getTitle(){return title;}

    public String getPosterPath(){return posterPath;}

    public int getYear(){return year;}
}
