package entity;

import java.util.List;

public class Movie {

    private final String imdbID;
    private final String title;
    private final String summary;
    // PG - 13, G, etc... (not enjoyment rating)
    private final String genres;
    private final String imdbScore, rottenTomatoesScore, metacriticScore;
    private final String director, actors;
    private final String posterURL;
    private final int year, runtime;

    public Movie(String imdbID, String title, String posterURL, int year) {
        this.title = title;
        this.posterURL = posterURL;
        this.year = year;
        this.imdbID = imdbID;

        // empty fields
        summary = genres = "";
        imdbScore = rottenTomatoesScore = metacriticScore = "";
        director = actors = "";
        runtime = 0;
    }

    public Movie(String imdbID, String title, String summary, String genres, String imdbScore, String rottenTomatoesScore,
                         String metacriticScore, String director, String actors, String posterURL, int year, int runtime) {
        this.imdbID = imdbID;
        this.title = title;
        this.summary = summary;
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

    public String getImdbID() {return imdbID; }
    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

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

    public int getYear() {
        return year;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getRating() { return ""; } //TODO: this should never have been on this class. The rating has a "UserRating" entity.
}
