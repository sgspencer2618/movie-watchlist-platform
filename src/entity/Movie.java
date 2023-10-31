package entity;

import java.util.List;

public class Movie {

    private final String title;
    private final String summary;
    private final List<Integer> ratings;

    public Movie(String title, String summary, List<Integer> ratings) {
        this.title = title;
        this.summary = summary;
        this.ratings = ratings;
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

}
