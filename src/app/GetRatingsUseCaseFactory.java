package app;

import interface_adapters.add_to_watchlist.AddToWatchlistController;
import interface_adapters.get_ratings.GetRatingsController;
import interface_adapters.get_ratings.GetRatingsPresenter;
import interface_adapters.get_ratings.GetRatingsViewModel;
import interface_adapters.remove_from_watchlist.RemoveFromWatchlistController;
import interface_adapters.remove_rating.RemoveRatingController;
import interface_adapters.update_rating.UpdateRatingController;
import use_case.get_ratings.*;
import use_case.get_watchlist.GetWatchlistDataAccessInterface;
import utility.ApiInterface;
import view.MovieInfoView;
import view.RatingsView;

public class GetRatingsUseCaseFactory {

    /** Prevent instantiation. */

    private GetRatingsUseCaseFactory() {}

    public static RatingsView create(ApiInterface api, GetRatingsViewModel getRatingsViewModel,
                                     GetRatingsDataAccessInterface ratingsDataAccessObject,
                                     GetWatchlistDataAccessInterface watchlistAccessObject,
                                     MovieInfoView movieInfoView, AddToWatchlistController addToWatchlistController,
                                     RemoveFromWatchlistController removeFromWatchlistController,
                                     UpdateRatingController updateRatingController,
                                     RemoveRatingController removeRatingController) {
        GetRatingsController getRatingsController = createGetRatingsUseCase(getRatingsViewModel, ratingsDataAccessObject, watchlistAccessObject, api);

        return new RatingsView(getRatingsController, getRatingsViewModel, movieInfoView, addToWatchlistController,
                removeFromWatchlistController, updateRatingController, removeRatingController);
    }

    public static GetRatingsController createGetRatingsUseCase(GetRatingsViewModel getRatingsViewModel, GetRatingsDataAccessInterface ratingsDataAccessObject,
                                                                   GetWatchlistDataAccessInterface watchlistAccessObject, ApiInterface api) {
        GetRatingsOutputBoundary getRatingsPresenter = new GetRatingsPresenter(getRatingsViewModel);
        GetRatingsInputBoundary getRatingsInteractor = new GetRatingsInteractor(ratingsDataAccessObject, watchlistAccessObject, getRatingsPresenter, api);

        return new GetRatingsController(getRatingsInteractor);
    }
}
