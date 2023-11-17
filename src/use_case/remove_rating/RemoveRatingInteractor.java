package use_case.remove_rating;

public class RemoveRatingInteractor implements RemoveRatingInputBoundary{
    final RemoveRatingDataAccessInterface removeRatingDataAccessObject;
    final RemoveRatingOutputBoundary removeRatingPresenter;


    public RemoveRatingInteractor(RemoveRatingDataAccessInterface removeRatingDataAccessObject,
                           RemoveRatingOutputBoundary removeRatingPresenter) {
        this.removeRatingDataAccessObject = removeRatingDataAccessObject;
        this.removeRatingPresenter = removeRatingPresenter;
    }

    @Override
    public void execute(RemoveRatingInputData removeRatingInputData) {
        String username = removeRatingInputData.getUsername();
        String movieId = removeRatingInputData.getMovieId();
        removeRatingDataAccessObject.removeRating(username, movieId);
        RemoveRatingOutputData removeRatingOutputData = new RemoveRatingOutputData(null);
        removeRatingPresenter.prepareSuccessView(removeRatingOutputData);
    }
}
