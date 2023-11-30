package interface_adapters.search;
import interface_adapters.ViewManagerModel;
import use_case.search.SearchHandlerOutputBoundary;
import use_case.search.SearchHandlerOutputData;

import java.util.List;
import entity.Watchlist;
import entity.UserRating;
import entity.Movie;


public class SearchPresenter implements SearchHandlerOutputBoundary{

    private final SearchViewModel searchViewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchPresenter(SearchViewModel viewModel, ViewManagerModel viewManager) {
        this.searchViewModel = viewModel;
        this.viewManagerModel = viewManager;
    }

    @Override
    public void prepareSuccessView(SearchHandlerOutputData outputData) {
        SearchState state = new SearchState();

        state.setWatchlist(outputData.getWatchlist());
        state.setRatings(outputData.getRatings());
        state.setMovieList(outputData.getMovieList());

        searchViewModel.setState(state);
        viewManagerModel.firePropertyChanged();
        viewManagerModel.setActiveView(searchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
