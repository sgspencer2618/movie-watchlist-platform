package use_case.update_rating;


public class UpdateRatingInteractor implements  UpdateRatingInputBoundary{
    final UpdateRatingDataAccessInterface updateRatingDataAccessObject;
    final UpdateRatingOutputBoundary updateRatingPresenter;


    public UpdateRatingInteractor(UpdateRatingDataAccessInterface updateRatingDataAccesssObject,
                                  UpdateRatingOutputBoundary updateRatingPresenter) {
        this.updateRatingDataAccessObject = updateRatingDataAccesssObject;
        this.updateRatingPresenter = updateRatingPresenter;
    }

    @Override
    public void execute(UpdateRatingInputData updateRatingInputData) {
        String username = updateRatingInputData.getUsername();
        String movieId = updateRatingInputData.getMovieId();
        int newRating = updateRatingInputData.getNewRating();
        updateRatingDataAccessObject.updateRating(username, movieId, newRating);
        UpdateRatingOutputData updateRatingOutputData = new UpdateRatingOutputData(newRating, false);
        updateRatingPresenter.prepareSuccessView(updateRatingOutputData);
    }
}
