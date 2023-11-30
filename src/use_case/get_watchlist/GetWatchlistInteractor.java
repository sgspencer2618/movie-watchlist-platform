package use_case.get_watchlist;

import data_access.UserRatingAccessObject;
import entity.Movie;
import entity.UserRating;
import entity.Watchlist;
import utility.ApiInterface;

import java.util.ArrayList;
import java.util.List;

public class GetWatchlistInteractor implements GetWatchlistInputBoundary {
    private final GetWatchlistDataAccessInterface getWatchlistDataAccessObject;
    private final UserRatingAccessObject ratingAccessObject;
    private final ApiInterface apiInterface;
    private final GetWatchlistOutputBoundary getWatchlistPresenter;

    public GetWatchlistInteractor(GetWatchlistDataAccessInterface getWatchlistDataAccessObject, UserRatingAccessObject ratingAccessObject, ApiInterface apiInterface, GetWatchlistOutputBoundary getWatchlistPresenter) {
        this.getWatchlistDataAccessObject = getWatchlistDataAccessObject;
        this.ratingAccessObject = ratingAccessObject;
        this.apiInterface = apiInterface;
        this.getWatchlistPresenter = getWatchlistPresenter;
    }

    @Override
    public void execute(GetWatchlistInputData getWatchlistInputData) {
        Watchlist watchlist = getWatchlistDataAccessObject.getWatchlist(getWatchlistInputData.getUser());

        List<Movie> movieList = new ArrayList<>();

        for (String movieId: watchlist.getMovieIDs()) {
            movieList.add(apiInterface.getMovie(movieId));
        }

        List<UserRating> ratings = ratingAccessObject.getRatings(getWatchlistInputData.getUser());

        //Movie List Trimmer - filters out movies in the watchlist that haven't been rated by the user
        List<UserRating> filteredRatings = new ArrayList<>() {
        };
        for (UserRating rating: ratings) {

            if (watchlist.getMovieIDs().contains(rating.getMovieId())) {
                filteredRatings.add(rating);
            }
        }

        GetWatchlistOutputData getWatchlistOutputData = new GetWatchlistOutputData(watchlist, filteredRatings, movieList);

        getWatchlistPresenter.prepareGetWatchlistView(getWatchlistOutputData);
    }
}
