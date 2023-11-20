package use_case.remove_rating;

public interface RemoveRatingOutputBoundary {
    void prepareSuccessView(RemoveRatingOutputData rating);

    void prepareFailView(String error);
}

