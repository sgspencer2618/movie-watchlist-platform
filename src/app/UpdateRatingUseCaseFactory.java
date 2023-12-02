package app;

import interface_adapters.update_rating.UpdateRatingController;
import interface_adapters.update_rating.UpdateRatingPresenter;
import interface_adapters.update_rating.UpdateRatingViewModel;
import use_case.update_rating.UpdateRatingDataAccessInterface;
import use_case.update_rating.UpdateRatingInputBoundary;
import use_case.update_rating.UpdateRatingInteractor;
import use_case.update_rating.UpdateRatingOutputBoundary;

public class UpdateRatingUseCaseFactory {
    private UpdateRatingUseCaseFactory() {}

    public static UpdateRatingController createUpdateRatingUseCase(UpdateRatingViewModel viewmodel, UpdateRatingDataAccessInterface updateRatingDataAccessInterface) {
        UpdateRatingOutputBoundary updateRatingPresenter = new UpdateRatingPresenter(viewmodel);
        UpdateRatingInputBoundary updateRatingInteractor = new UpdateRatingInteractor(updateRatingDataAccessInterface, updateRatingPresenter);
        return new UpdateRatingController(updateRatingInteractor);
    }
}
