package interface_adapters.remove_rating;

import use_case.remove_rating.RemoveRatingInputBoundary;
import use_case.remove_rating.RemoveRatingInputData;

public class RemoveRatingController {
    final RemoveRatingInputBoundary removeRatingUseCaseInteractor;
    public RemoveRatingController(RemoveRatingInputBoundary removeRatingUseCaseInteractor) {
        this.removeRatingUseCaseInteractor = removeRatingUseCaseInteractor;
    }

    public void execute(String username, String movieId) {
        RemoveRatingInputData removeRatingInputData = new RemoveRatingInputData(username, movieId);
        removeRatingUseCaseInteractor.execute(removeRatingInputData);
    }

}
