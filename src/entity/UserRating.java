package entity;

public class UserRating {
    private final int id;
    private final int imdb_id;
    private final String username;
    private final int rating;


    public UserRating(int id, int imdbId, String username, int rating) {
        this.id = id;
        this.imdb_id = imdbId;
        this.username = username;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public int getImdb_id() {
        return imdb_id;
    }

    public String getUsername() {
        return username;
    }

    public int getRating() {
        return rating;
    }
}
