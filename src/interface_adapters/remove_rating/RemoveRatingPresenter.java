package interface_adapters.remove_rating;

import use_case.remove_rating.RemoveRatingOutputData;
import use_case.remove_rating.RemoveRatingOutputBoundary;

public class RemoveRatingPresenter implements RemoveRatingOutputBoundary {

    @Override
    public void prepareSuccessView(RemoveRatingOutputData response) {
        // Do nothing
    }
    @Override
    public void prepareFailView(String error) {
        System.err.println(error);
    }
}
