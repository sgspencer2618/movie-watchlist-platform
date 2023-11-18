package use_case.get_ratings;

import entity.Movie;

import java.util.List;

public class GetRatingsInteractor implements GetRatingsInputBoundary {

    private final GetRatingsDataAccessInterface watchlistAccessObject;

    private final GetRatingsOutputBoundary getRatingsPresenter;


    public GetRatingsInteractor(GetRatingsDataAccessInterface watchlistAccessObject, GetRatingsOutputBoundary getRatingsPresenter) {
        this.watchlistAccessObject = watchlistAccessObject;
        this.getRatingsPresenter = getRatingsPresenter;
    }

    @Override
    public void execute(GetRatingsInputData getRatingsInputData) {
        String currUser = getRatingsInputData.getCurrUsername();

        List<Movie> watchlist = watchlistAccessObject.getWatchlist(currUser);
        Boolean useCaseFailed = false;

        GetRatingsOutputData outputData = new GetRatingsOutputData(useCaseFailed, watchlist);
        getRatingsPresenter.prepareSuccessView(outputData);
    }

}
