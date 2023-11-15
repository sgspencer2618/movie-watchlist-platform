package entity;

import java.util.List;

public class Movie {

    private final String title;
    private final String summary;
    private final String imdbID;

    private final List<Integer> ratings;

    public Movie(String title, String summary, List<Integer> ratings, String imdbID){
        this.title = title;
        this.summary = summary;
        this.ratings = ratings;
        this.imdbID = imdbID;
    }

    public String getTitle() {

        return title;
    }

    public String getSummary() {

        return summary;
    }

    public List<Integer> getRatings() {

        return ratings;
    }

    public String getImdbID() {

        return imdbID;
    }

}
