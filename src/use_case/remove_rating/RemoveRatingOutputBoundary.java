package use_case.remove_rating;

public interface RemoveRatingOutputBoundary {
    void prepareSuccessView(RemoveRatingOutputData deletedRating);

    void prepareFailView(String error);
}

