package use_case.movie_info;

public class MovieInfoOutputData {

    private String title;
    private String summary;

    // PG - 13, G, etc... (not enjoyment rating)
    private String rating;
    private String imdbScore, rottenTomatoesScore, metacriticScore;
    private String director, actors;
    private int year, runtime;

    public MovieInfoOutputData(String title, String summary, String rating, String imdbScore, String rottenTomatoesScore,
                               String metacriticScore, String director, String actors, int year, int runtime) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.imdbScore = imdbScore;
        this.rottenTomatoesScore = rottenTomatoesScore;
        this.metacriticScore = metacriticScore;
        this.director = director;
        this.actors = actors;
        this.year = year;
        this.runtime = runtime;
    }
}
