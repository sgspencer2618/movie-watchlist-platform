package app;

import interface_adapters.add_to_watchlist.AddToWatchlistController;
import interface_adapters.remove_from_watchlist.RemoveFromWatchlistController;
import utility.ApiInterface;
import view.SearchView;
import data_access.UserRatingAccessObject;
import data_access.WatchlistAccessObject;
import interface_adapters.ViewManagerModel;
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
                                    RemoveFromWatchlistController removeFromWatchlistController) {
        SearchController searchController = createSearchController(searchModel, watchlistAccessObject, userRatingAccessObject, api);
        return new SearchView(searchController, searchModel, movieInfoView, addToWatchlistController, removeFromWatchlistController);
    }

    public static SearchController createSearchController (SearchViewModel searchViewModel, WatchlistAccessObject watchlistAccessObject, UserRatingAccessObject userRatingAccessObject, ApiInterface api) {
        SearchHandlerOutputBoundary searchPresenter = new SearchPresenter(searchViewModel);
        SearchHandlerInputBoundary searchInteractor = new SearchInteractor(api, userRatingAccessObject, watchlistAccessObject, searchPresenter);

        return new SearchController(searchInteractor);
    }

}
