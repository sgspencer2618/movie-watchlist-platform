package app;

import data_access.UserRatingAccessObject;
import data_access.WatchlistAccessObject;
import interface_adapters.ViewManagerModel;
import interface_adapters.get_watchlist.GetWatchlistController;
import interface_adapters.get_watchlist.GetWatchlistPresenter;
import interface_adapters.get_watchlist.GetWatchlistViewModel;
import interface_adapters.movie_info.MovieInfoViewModel;
import use_case.get_ratings.GetRatingsDataAccessInterface;
import use_case.get_watchlist.*;
import utility.ApiInterface;
import view.WatchlistView;

public class GetWatchlistUseCaseFactory {

    /** Prevent instantiation. */

    private GetWatchlistUseCaseFactory() {}

    public static WatchlistView create(ApiInterface api, GetWatchlistViewModel getWatchlistViewModel, ViewManagerModel viewManagerModel,
                                       GetWatchlistDataAccessInterface watchlistDataAccessObject, UserRatingAccessObject ratingAccessObject,
                                       MovieInfoViewModel movieInfoViewModel) {
        GetWatchlistController getWatchlistController = createGetWatchlistUseCase(getWatchlistViewModel, viewManagerModel, watchlistDataAccessObject, ratingAccessObject, api);
        return new WatchlistView(getWatchlistController, MovieInfoUseCaseFactory.createMovieInfoUseCase(api, movieInfoViewModel), getWatchlistViewModel);
    }

    public static GetWatchlistController createGetWatchlistUseCase(GetWatchlistViewModel getWatchlistViewModel, ViewManagerModel viewManagerModel, GetWatchlistDataAccessInterface getWatchlistAccessObject,
                                                                   UserRatingAccessObject ratingAccessObject, ApiInterface api) {
        GetWatchlistOutputBoundary getWatchlistPresenter = new GetWatchlistPresenter(getWatchlistViewModel, viewManagerModel);
        GetWatchlistInputBoundary getWatchlistInteractor = new GetWatchlistInteractor(getWatchlistAccessObject, ratingAccessObject, api, getWatchlistPresenter);

        return new GetWatchlistController(getWatchlistInteractor);
    }
}
