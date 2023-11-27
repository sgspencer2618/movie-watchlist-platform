package use_case.get_ratings;

import entity.Movie;
import entity.UserRating;
import entity.Watchlist;
import use_case.get_watchlist.GetWatchlistDataAccessInterface;
import use_case.get_watchlist.GetWatchlistInteractor;
import utility.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetRatingsInteractor implements GetRatingsInputBoundary {

    private final GetRatingsDataAccessInterface ratingsDataAccessObject;

    private final ApiInterface apiInterface;

    private final GetWatchlistDataAccessInterface getwatchlistDataAccessObject;

    private final GetRatingsOutputBoundary getRatingsPresenter;


    public GetRatingsInteractor(GetRatingsDataAccessInterface ratingsAccessObject,
                                GetWatchlistDataAccessInterface watchlistAccessObject,
                                GetRatingsOutputBoundary getRatingsPresenter,
                                ApiInterface apiInterface) {
        this.ratingsDataAccessObject = ratingsAccessObject;
        this.getRatingsPresenter = getRatingsPresenter;
        this.getwatchlistDataAccessObject = watchlistAccessObject;
        this.apiInterface = apiInterface;
    }

    @Override
    public void execute(GetRatingsInputData getRatingsInputData) {
        Watchlist watchlist = getwatchlistDataAccessObject.getWatchlist(getRatingsInputData.getCurrUsername());

        List<Movie> movieList = new ArrayList<>();

        for (String movieId: watchlist.getMovieIDs()) {
            movieList.add(apiInterface.getMovie(movieId));
        }
        List<UserRating> ratings = ratingsDataAccessObject.getRatings(getRatingsInputData.getCurrUsername());

        ///Hashmap trimmer
        // List<Movie> filteredMovieList = new ArrayList<>();
        // for (Map.Entry<Movie, Integer> curr : ratings.) {
        //     if (watchlist.getMovieIDs().contains(curr.getKey().getImdbID())) {
        //         filteredMovieList.add(curr.getKey());
        //     }
        // }

        // GetRatingsOutputData getRatingsOutputData = new GetRatingsOutputData(ratings, filteredMovieList);

        // getRatingsPresenter.prepareGetRatingsView(getRatingsOutputData);
    }

}
