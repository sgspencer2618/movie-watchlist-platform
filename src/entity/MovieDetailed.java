package entity;

public class MovieDetailed {

    private String title;
    private String summary;

    // PG - 13, G, etc... (not enjoyment rating)
    private String rating, genres;
    private String imdbScore, rottenTomatoesScore, metacriticScore;
    private String director, actors;
    private int year, runtime;

    public MovieDetailed(String title, String summary, String rating, String genres, String imdbScore, String rottenTomatoesScore,
                         String metacriticScore, String director, String actors, int year, int runtime) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.genres = genres;
        this.imdbScore = imdbScore;
        this.rottenTomatoesScore = rottenTomatoesScore;
        this.metacriticScore = metacriticScore;
        this.director = director;
        this.actors = actors;
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

    public int getYear() {
        return year;
    }

    public int getRuntime() {
        return runtime;
    }
}
