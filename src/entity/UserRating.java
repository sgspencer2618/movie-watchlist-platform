package entity;

public class UserRating {
    private final String movieId;
    private final String username;
    private int rating;


    public UserRating(String movieId, String username, int rating) {
        this.movieId = movieId;
        this.username = username;
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getUsername() {
        return username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(Integer rat) {
        this.rating = rat;
    }
}
