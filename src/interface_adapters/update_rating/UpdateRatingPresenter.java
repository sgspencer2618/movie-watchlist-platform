package interface_adapters.update_rating;

import use_case.update_rating.UpdateRatingOutputBoundary;
import use_case.update_rating.UpdateRatingOutputData;

public class UpdateRatingPresenter implements UpdateRatingOutputBoundary {
    private final UpdateRatingViewModel updateRatingViewModel;


    public UpdateRatingPresenter(UpdateRatingViewModel updateRatingViewModel) {
        this.updateRatingViewModel = updateRatingViewModel;
    }

    @Override
    public void prepareSuccessView(UpdateRatingOutputData response) {
        UpdateRatingState updateRatingState = updateRatingViewModel.getState();
        if(updateRatingState != null) {
            updateRatingState.setNewRating(response.getNewRating());
            this.updateRatingViewModel.setState(updateRatingState);
            updateRatingViewModel.firePropertyChanged();
        }
    }
}
