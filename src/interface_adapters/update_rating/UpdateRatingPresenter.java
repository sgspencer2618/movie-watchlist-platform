package interface_adapters.update_rating;

import interface_adapters.remove_rating.RemoveRatingState;
import interface_adapters.remove_rating.RemoveRatingViewModel;
import use_case.movie_info.MovieInfoOutputBoundary;
import use_case.remove_rating.RemoveRatingOutputData;
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
        updateRatingState.setNewRating(response.getNewRating());
        this.updateRatingViewModel.setState(updateRatingState);
        updateRatingViewModel.firePropertyChanged();
    }
}

