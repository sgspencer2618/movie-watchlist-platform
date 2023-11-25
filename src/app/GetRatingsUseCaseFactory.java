package app;

import data_access.UserRatingAccessObject;
import interface_adapters.ViewManagerModel;
import interface_adapters.get_ratings.GetRatingsController;
import interface_adapters.get_ratings.GetRatingsPresenter;
import interface_adapters.get_ratings.GetRatingsViewModel;
import interface_adapters.movie_info.MovieInfoViewModel;
import use_case.get_ratings.*;
import use_case.get_watchlist.GetWatchlistDataAccessInterface;
import utility.ApiInterface;
import view.RatingsView;

public class GetRatingsUseCaseFactory {

    /** Prevent instantiation. */

    private GetRatingsUseCaseFactory() {}

    public static RatingsView create(ApiInterface api, GetRatingsViewModel getRatingsViewModel, ViewManagerModel viewManagerModel,
                                       GetRatingsDataAccessInterface ratingsDataAccessObject,
                                       GetWatchlistDataAccessInterface watchlistAccessObject,
                                       MovieInfoViewModel movieInfoViewModel) {
        GetRatingsController getRatingsController = createGetRatingsUseCase(getRatingsViewModel, viewManagerModel, ratingsDataAccessObject, watchlistAccessObject, api);
        return new RatingsView(getRatingsController, MovieInfoUseCaseFactory.createMovieInfoUseCase(api, movieInfoViewModel), getRatingsViewModel);
    }

    public static GetRatingsController createGetRatingsUseCase(GetRatingsViewModel getRatingsViewModel, ViewManagerModel viewManagerModel, GetRatingsDataAccessInterface ratingsDataAccessObject,
                                                                   GetWatchlistDataAccessInterface watchlistAccessObject, ApiInterface api) {
        GetRatingsOutputBoundary getRatingsPresenter = new GetRatingsPresenter(getRatingsViewModel, viewManagerModel);
        GetRatingsInputBoundary getRatingsInteractor = new GetRatingsInteractor(ratingsDataAccessObject, watchlistAccessObject, getRatingsPresenter, api);

        return new GetRatingsController(getRatingsInteractor);
    }
}
