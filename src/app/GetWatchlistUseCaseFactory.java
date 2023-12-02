package app;

import data_access.UserRatingAccessObject;
import interface_adapters.add_to_watchlist.AddToWatchlistController;
import interface_adapters.get_watchlist.GetWatchlistController;
import interface_adapters.get_watchlist.GetWatchlistPresenter;
import interface_adapters.get_watchlist.GetWatchlistViewModel;
import interface_adapters.remove_from_watchlist.RemoveFromWatchlistController;
import use_case.get_watchlist.*;
import utility.ApiInterface;
import view.MovieInfoView;
import view.WatchlistView;

public class GetWatchlistUseCaseFactory {

    /** Prevent instantiation. */

    private GetWatchlistUseCaseFactory() {}

    public static WatchlistView create(ApiInterface api, GetWatchlistViewModel getWatchlistViewModel,
                                       GetWatchlistDataAccessInterface watchlistDataAccessObject, UserRatingAccessObject ratingAccessObject,
                                       MovieInfoView movieInfoView, AddToWatchlistController addToWatchlistController, RemoveFromWatchlistController removeFromWatchlistController) {
        GetWatchlistController getWatchlistController = createGetWatchlistUseCase(getWatchlistViewModel, watchlistDataAccessObject, ratingAccessObject, api);
        return new WatchlistView(getWatchlistController, getWatchlistViewModel, movieInfoView, addToWatchlistController, removeFromWatchlistController);
    }

    public static GetWatchlistController createGetWatchlistUseCase(GetWatchlistViewModel getWatchlistViewModel, GetWatchlistDataAccessInterface getWatchlistAccessObject,
                                                                   UserRatingAccessObject ratingAccessObject, ApiInterface api) {
        GetWatchlistOutputBoundary getWatchlistPresenter = new GetWatchlistPresenter(getWatchlistViewModel);
        GetWatchlistInputBoundary getWatchlistInteractor = new GetWatchlistInteractor(getWatchlistAccessObject, ratingAccessObject, api, getWatchlistPresenter);

        return new GetWatchlistController(getWatchlistInteractor);
    }
}
