package interface_adapters.search;
import use_case.search.SearchHandlerOutputBoundary;
import use_case.search.SearchHandlerOutputData;



public class SearchPresenter implements SearchHandlerOutputBoundary{

    private final SearchViewModel searchViewModel;

    public SearchPresenter(SearchViewModel viewModel) {
        this.searchViewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(SearchHandlerOutputData outputData) {
        SearchState state = searchViewModel.getState();

        state.setWatchlist(outputData.getWatchlist());
        state.setRatings(outputData.getRatings());
        state.setMovieList(outputData.getMovieList());

        searchViewModel.firePropertyChanged();

    }
}
