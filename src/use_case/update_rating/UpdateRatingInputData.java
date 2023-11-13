package use_case.update_rating;

public class UpdateRatingInputData {
    final private int newRating;
    final private int userId;
    final private int movieId;

    public UpdateRatingInputData(int newRating, int userId, int movieId) {
        this.newRating = newRating;
        this.userId = userId;
        this.movieId = movieId;
    }

}
