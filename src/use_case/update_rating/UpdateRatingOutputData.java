package use_case.update_rating;

public class UpdateRatingOutputData {
    int newRating;
    private final boolean useCaseFailed;

    public UpdateRatingOutputData(int newRating, boolean useCaseFailed){
        this.newRating = newRating;
        this.useCaseFailed = useCaseFailed;
    }
    public int getNewRating() {
        return newRating;
    }
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
