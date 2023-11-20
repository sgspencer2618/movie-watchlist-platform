package use_case.get_watchlist;

import data_access.UserRatingAccessObject;
import entity.Movie;
import entity.Watchlist;

import java.util.HashMap;
import java.util.Map;

public class GetWatchlistInteractor implements GetWatchlistInputBoundary {
    private final GetWatchlistDataAccessInterface getWatchlistDataAccessObject;
    UserRatingAccessObject ratingAccessObject;
    private final GetWatchlistOutputBoundary getWatchlistPresenter;

    public GetWatchlistInteractor(GetWatchlistDataAccessInterface getWatchlistDataAccessObject, GetWatchlistOutputBoundary getWatchlistPresenter) {
        this.getWatchlistDataAccessObject = getWatchlistDataAccessObject;
        this.getWatchlistPresenter = getWatchlistPresenter;
    }

    @Override
    public void execute(GetWatchlistInputData getWatchlistInputData) {
        Watchlist watchlist = getWatchlistDataAccessObject.getWatchlist(getWatchlistInputData.getUser());
        HashMap<Movie, Integer> ratings = ratingAccessObject.getRatings(getWatchlistInputData.getUser());

        //Hashmap trimmer
        HashMap<Movie, Integer> filteredRatings = new HashMap<>();
        for (Map.Entry<Movie, Integer> curr : ratings.entrySet()) {

            if (watchlist.getMovieIDs().contains(curr.getKey().getImdbID())) {
                filteredRatings.put(curr.getKey(), curr.getValue());
            }
        }

        GetWatchlistOutputData getWatchlistOutputData = new GetWatchlistOutputData(watchlist, filteredRatings);

        getWatchlistPresenter.prepareGetWatchlistView(getWatchlistOutputData);
    }
}
