package use_case.get_ratings;

import entity.Movie;

import java.util.HashMap;
import java.util.List;

public class GetRatingsInteractor implements GetRatingsInputBoundary {

    private final GetRatingsDataAccessInterface ratingsAccessObject;

    private final GetRatingsOutputBoundary getRatingsPresenter;


    public GetRatingsInteractor(GetRatingsDataAccessInterface ratingsAccessObject, GetRatingsOutputBoundary getRatingsPresenter) {
        this.ratingsAccessObject = ratingsAccessObject;
        this.getRatingsPresenter = getRatingsPresenter;
    }

    @Override
    public void execute(GetRatingsInputData getRatingsInputData) {
        String currUser = getRatingsInputData.getCurrUsername();

        HashMap<Movie, Integer> ratings = ratingsAccessObject.getRatings(currUser);
        Boolean useCaseFailed = false;

        GetRatingsOutputData outputData = new GetRatingsOutputData(useCaseFailed, ratings);
        getRatingsPresenter.prepareSuccessView(outputData);
    }

}
