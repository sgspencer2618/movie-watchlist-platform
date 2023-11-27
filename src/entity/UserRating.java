package entity;

public class UserRating {
    private final int id;
    private final String movieId;
    private final String username;
    private final int rating;


    public UserRating(int id, String movieId, String username, int rating) {
        this.id = id;
        this.movieId = movieId;
        this.username = username;
        this.rating = rating;
    }

    public int getId() {
        return id;
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
}
