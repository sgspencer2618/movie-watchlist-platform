package interface_adapters.update_rating;
import use_case.update_rating.UpdateRatingInputBoundary;
import use_case.update_rating.UpdateRatingInputData;


public class UpdateRatingController {
    final UpdateRatingInputBoundary updateRatingUseCaseInteractor;
    public UpdateRatingController(UpdateRatingInputBoundary updateRatingUseCaseInteractor) {
        this.updateRatingUseCaseInteractor = updateRatingUseCaseInteractor;
    }

    public void execute(int newRating, String username, String movieId) {
        UpdateRatingInputData updateRatingInputData = new UpdateRatingInputData(
                newRating, username, movieId);

        updateRatingUseCaseInteractor.execute(updateRatingInputData);

    }
}
