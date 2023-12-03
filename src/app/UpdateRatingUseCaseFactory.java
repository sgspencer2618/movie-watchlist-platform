package app;

import interface_adapters.update_rating.UpdateRatingController;
import interface_adapters.update_rating.UpdateRatingPresenter;
import use_case.update_rating.UpdateRatingDataAccessInterface;
import use_case.update_rating.UpdateRatingInputBoundary;
import use_case.update_rating.UpdateRatingInteractor;
import use_case.update_rating.UpdateRatingOutputBoundary;

public class UpdateRatingUseCaseFactory {
    private UpdateRatingUseCaseFactory() {}

    public static UpdateRatingController createUpdateRatingUseCase(UpdateRatingDataAccessInterface updateRatingDataAccessInterface) {
        UpdateRatingOutputBoundary updateRatingPresenter = new UpdateRatingPresenter();
        UpdateRatingInputBoundary updateRatingInteractor = new UpdateRatingInteractor(updateRatingDataAccessInterface, updateRatingPresenter);
        return new UpdateRatingController(updateRatingInteractor);
    }
}
