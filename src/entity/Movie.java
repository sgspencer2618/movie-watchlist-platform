package entity;

import java.util.List;

public class Movie {

    private final String title;
    private final String summary;
    // PG - 13, G, etc... (not enjoyment rating)
    private final String rating, genres;
    private final String imdbScore, rottenTomatoesScore, metacriticScore;
    private final String director, actors;
    private final String posterURL;
    private final int year, runtime;


    public Movie(String title, String posterURL, int year) {
        this.title = title;
        this.posterURL = posterURL;
        this.year = year;

        // empty fields
        summary = rating = genres = "";
        imdbScore = rottenTomatoesScore = metacriticScore = "";
        director = actors = "";
        runtime = 0;
    }

    public Movie(String title, String summary, String rating, String genres, String imdbScore, String rottenTomatoesScore,
                         String metacriticScore, String director, String actors, String posterURL, int year, int runtime) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
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

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getRating() {
        return rating;
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
}
