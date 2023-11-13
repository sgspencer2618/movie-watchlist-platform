package interface_adapters.remove_rating;

import use_case.remove_rating.RemoveRatingInputBoundary;
import use_case.remove_rating.RemoveRatingInputData;
import use_case.remove_rating.RemoveRatingInteractor;

public class RemoveRatingController {
    final RemoveRatingInputBoundary removeRatingUseCaseInteractor;
    public RemoveRatingController(RemoveRatingInputBoundary removeRatingUseCaseInteractor) {
        this.removeRatingUseCaseInteractor = removeRatingUseCaseInteractor;
    }

    public void execute() {
        removeRatingUseCaseInteractor.execute();
    }

}
