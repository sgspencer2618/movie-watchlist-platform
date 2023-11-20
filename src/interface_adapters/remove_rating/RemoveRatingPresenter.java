package interface_adapters.remove_rating;

import interface_adapters.movie_info.MovieInfoState;
import use_case.remove_rating.RemoveRatingOutputData;
import use_case.remove_rating.RemoveRatingDataAccessInterface;
import use_case.remove_rating.RemoveRatingOutputBoundary;
import interface_adapters.ViewManagerModel;

public class RemoveRatingPresenter implements RemoveRatingOutputBoundary {
    private final RemoveRatingViewModel removeRatingViewModel;

    private ViewManagerModel viewManagerModel;

    public RemoveRatingPresenter (ViewManagerModel viewManagerModel,
                           RemoveRatingViewModel removeRatingViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.removeRatingViewModel = removeRatingViewModel;

    }
    @Override
    public void prepareSuccessView(RemoveRatingOutputData deletedRating) {


    }

    @Override
    public void prepareFailView(String error) {
        RemoveRatingState removeRatingState = removeRatingViewModel.getState();
        removeRatingState.setRemoveRatingError(error);
        removeRatingState.setRemovedRating(false);
        removeRatingViewModel.firePropertyChanged();
    }
}
