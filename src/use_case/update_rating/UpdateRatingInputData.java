package use_case.update_rating;

public class UpdateRatingInputData {
    final private int newRating;
    final private String username;
    final private String movieId;

    public UpdateRatingInputData(int newRating, String username, String movieId) {
        this.newRating = newRating;
        this.username = username;
        this.movieId = movieId;
    }

    int getNewRating() {
        return newRating;
    }
    String getUsername() {
        return username;
    }

    String getMovieId() {
        return movieId;
    }
}
