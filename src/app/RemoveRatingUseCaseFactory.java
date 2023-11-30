package app;

import interface_adapters.remove_rating.RemoveRatingController;
import interface_adapters.remove_rating.RemoveRatingPresenter;
import interface_adapters.remove_rating.RemoveRatingViewModel;
import use_case.remove_rating.RemoveRatingDataAccessInterface;
import use_case.remove_rating.RemoveRatingInputBoundary;
import use_case.remove_rating.RemoveRatingInteractor;
import use_case.remove_rating.RemoveRatingOutputBoundary;


public class RemoveRatingUseCaseFactory {

    /** Prevent instantiation. */
    private RemoveRatingUseCaseFactory() {}

    private static RemoveRatingController createRemoveRatingUseCase(RemoveRatingViewModel viewmodel, RemoveRatingDataAccessInterface removeRatingDataAccessInterface) {
        RemoveRatingOutputBoundary removeRatingPresenter = new RemoveRatingPresenter(viewmodel);
        RemoveRatingInputBoundary removeRatingInteractor = new RemoveRatingInteractor(removeRatingDataAccessInterface, removeRatingPresenter);
        return new RemoveRatingController(removeRatingInteractor);
    }
}