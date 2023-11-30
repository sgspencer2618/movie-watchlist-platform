package app;

import utility.ApiInterface;
import view.SearchView;
import data_access.UserRatingAccessObject;
import data_access.WatchlistAccessObject;
import interface_adapters.ViewManagerModel;
import interface_adapters.movie_info.MovieInfoViewModel;
import interface_adapters.search.SearchController;
import interface_adapters.search.SearchPresenter;
import interface_adapters.search.SearchViewModel;
import use_case.search.SearchHandlerInputBoundary;
import use_case.search.SearchHandlerOutputBoundary;
import use_case.search.SearchInteractor;

public class SearchUseCaseFactory {
    public static SearchView create(ApiInterface api, SearchViewModel searchModel, ViewManagerModel viewManagerModel,
                                    UserRatingAccessObject userRatingAccessObject, WatchlistAccessObject watchlistAccessObject,
                                    MovieInfoViewModel movieInfoViewModel) {
        SearchController searchController = createSearchController(searchModel, viewManagerModel, watchlistAccessObject, userRatingAccessObject, api);
        return new SearchView(searchController, MovieInfoUseCaseFactory.createMovieInfoUseCase(api, movieInfoViewModel), searchModel);
    }

    public static SearchController createSearchController (SearchViewModel searchViewModel, ViewManagerModel viewManagerModel, WatchlistAccessObject watchlistAccessObject, UserRatingAccessObject userRatingAccessObject, ApiInterface api) {
        SearchHandlerOutputBoundary searchPresenter = new SearchPresenter(searchViewModel, viewManagerModel);
        SearchHandlerInputBoundary searchInteractor = new SearchInteractor(api, userRatingAccessObject, watchlistAccessObject, searchPresenter);

        return new SearchController(searchInteractor);
    }

}
