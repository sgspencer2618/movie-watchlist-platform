package app;

import interface_adapters.remove_rating.RemoveRatingController;
import interface_adapters.remove_rating.RemoveRatingPresenter;
import use_case.remove_rating.RemoveRatingDataAccessInterface;
import use_case.remove_rating.RemoveRatingInputBoundary;
import use_case.remove_rating.RemoveRatingInteractor;
import use_case.remove_rating.RemoveRatingOutputBoundary;


public class RemoveRatingUseCaseFactory {

    /** Prevent instantiation. */
    private RemoveRatingUseCaseFactory() {}

    public static RemoveRatingController createRemoveRatingUseCase(RemoveRatingDataAccessInterface removeRatingDataAccessInterface) {
        RemoveRatingOutputBoundary removeRatingPresenter = new RemoveRatingPresenter();
        RemoveRatingInputBoundary removeRatingInteractor = new RemoveRatingInteractor(removeRatingDataAccessInterface, removeRatingPresenter);
        return new RemoveRatingController(removeRatingInteractor);
    }
}