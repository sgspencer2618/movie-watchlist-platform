package use_case.get_ratings;

public interface GetRatingsOutputBoundary {

    void prepareSuccessView(GetRatingsOutputData watchlist);

    void prepareFailView(String error);
}
