package use_case.remove_rating;

public class RemoveRatingInputData {
    final private String username;
    final private String movieId;

    public RemoveRatingInputData(String username, String movieId) {
        this.username = username;
        this.movieId = movieId;
    }

    String getUsername() {
        return username;
    }

    String getMovieId() {
        return movieId;
    }

}
