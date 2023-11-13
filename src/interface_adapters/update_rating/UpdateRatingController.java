package interface_adapters.update_rating;
import use_case.update_rating.UpdateRatingInputBoundary;
import use_case.update_rating.UpdateRatingInputData;
import use_case.update_rating.UpdateRatingInteractor;
import use_case.update_rating.UpdateRatingOutputData;


public class UpdateRatingController {
    final UpdateRatingInputBoundary updateRatingUseCaseInteractor;
    public UpdateRatingController(UpdateRatingInputBoundary updateRatingUseCaseInteractor) {
        this.updateRatingUseCaseInteractor = updateRatingUseCaseInteractor;
    }

    public void execute() {

    }
}
