package entity;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {

    private final String imdbID;
    private final String title;
    private final String summary;
    // PG - 13, G, etc... (not enjoyment rating)
    private final String contentRating, genres;
    private final String imdbScore, rottenTomatoesScore, metacriticScore;
    private final String director, actors;
    private final String posterURL;
    private final Integer year, runtime;
  
    private int userRating;

    private boolean inWatchlist;

    public Movie(String imdbID, String title, String posterURL, int year)
        this.title = title;
        this.posterURL = posterURL;
        this.year = year;
        this.imdbID = imdbID;

        // empty fields
        summary = contentRating = genres = "";
        imdbScore = rottenTomatoesScore = metacriticScore = "";
        director = actors = "";
        runtime = 0;
    }

    public Movie(String imdbID, String title, String summary, String contentRating, String genres,
                 String imdbScore, String rottenTomatoesScore, String metacriticScore, String director,
                 String actors, String posterURL, Integer year, Integer runtime) {
        this.imdbID = imdbID;
        this.title = title;
        this.summary = summary;
        this.contentRating = contentRating;
        this.genres = genres;
        this.imdbScore = imdbScore;
        this.rottenTomatoesScore = rottenTomatoesScore;
        this.metacriticScore = metacriticScore;
        this.director = director;
        this.actors = actors;
        this.posterURL = posterURL;
        this.year = year;
        this.runtime = runtime;
    }

    public String getImdbID() { return imdbID; }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getContentRating() { return contentRating; }

    public String getGenres() { return genres; }

    public String getImdbScore() {
        return imdbScore;
    }

    public String getRottenTomatoesScore() {
        return rottenTomatoesScore;
    }

    public String getMetacriticScore() {
        return metacriticScore;
    }

    public String getDirector() {
        return director;
    }

    public String getActors() {
        return actors;
    }

    public String getPosterURL() { return posterURL; }

    public Integer getYear() {
        return year;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public int getUserRating() {
        return userRating;
    }
    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public boolean isInWatchlist() {
        return inWatchlist;
    }

    public void setInWatchlist(boolean inWatchlist) {
        this.inWatchlist = inWatchlist;
    }
}
