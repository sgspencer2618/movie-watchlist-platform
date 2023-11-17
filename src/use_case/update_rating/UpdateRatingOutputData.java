package use_case.update_rating;

public class UpdateRatingOutputData {
    int newRating;

    public UpdateRatingOutputData(int newRating){
        this.newRating = newRating;
    }

    public int getNewRating() {
        return newRating;
    }
}
