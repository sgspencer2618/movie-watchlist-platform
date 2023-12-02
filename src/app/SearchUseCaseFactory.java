package app;

import interface_adapters.add_to_watchlist.AddToWatchlistController;
import interface_adapters.remove_from_watchlist.RemoveFromWatchlistController;
import interface_adapters.remove_rating.RemoveRatingController;
import interface_adapters.update_rating.UpdateRatingController;
import utility.ApiInterface;
import view.SearchView;
import data_access.UserRatingAccessObject;
import data_access.WatchlistAccessObject;
import view.MovieInfoView;
import interface_adapters.search.SearchController;
import interface_adapters.search.SearchPresenter;
import interface_adapters.search.SearchViewModel;
import use_case.search.SearchHandlerInputBoundary;
import use_case.search.SearchHandlerOutputBoundary;
import use_case.search.SearchInteractor;

public class SearchUseCaseFactory {
    public static SearchView create(ApiInterface api, SearchViewModel searchModel,
                                    UserRatingAccessObject userRatingAccessObject, WatchlistAccessObject watchlistAccessObject,
                                    MovieInfoView movieInfoView, AddToWatchlistController addToWatchlistController,
                                    RemoveFromWatchlistController removeFromWatchlistController,
                                    UpdateRatingController updateRatingController, RemoveRatingController removeRatingController) {
        SearchController searchController = createSearchController(searchModel, watchlistAccessObject, userRatingAccessObject, api);
        return new SearchView(searchController, searchModel, movieInfoView, addToWatchlistController, removeFromWatchlistController, updateRatingController, removeRatingController);
    }

    public static SearchController createSearchController (SearchViewModel searchViewModel, WatchlistAccessObject watchlistAccessObject, UserRatingAccessObject userRatingAccessObject, ApiInterface api) {
        SearchHandlerOutputBoundary searchPresenter = new SearchPresenter(searchViewModel);
        SearchHandlerInputBoundary searchInteractor = new SearchInteractor(api, userRatingAccessObject, watchlistAccessObject, searchPresenter);

        return new SearchController(searchInteractor);
    }

}
