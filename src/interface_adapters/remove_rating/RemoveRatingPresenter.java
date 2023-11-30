package interface_adapters.remove_rating;

import use_case.remove_rating.RemoveRatingOutputData;
import use_case.remove_rating.RemoveRatingOutputBoundary;

public class RemoveRatingPresenter implements RemoveRatingOutputBoundary {
    private final RemoveRatingViewModel removeRatingViewModel;

    public RemoveRatingPresenter (RemoveRatingViewModel removeRatingViewModel) {
        this.removeRatingViewModel = removeRatingViewModel;
    }

    @Override
    public void prepareSuccessView(RemoveRatingOutputData response) {
        RemoveRatingState removeRatingState = removeRatingViewModel.getState();
        removeRatingState.setRemovedRating(response.getDeletedRating());
        this.removeRatingViewModel.setState(removeRatingState);
        removeRatingViewModel.firePropertyChanged();

    }
    @Override
    public void prepareFailView(String error) {
        RemoveRatingState removeRatingState = removeRatingViewModel.getState();
        removeRatingState.setRemoveRatingError(error);
        removeRatingState.setRemovedRating(false);
        removeRatingViewModel.firePropertyChanged();
    }
}
