package interface_adapters.get_ratings;

import entity.Movie;
import interface_adapters.ViewManagerModel;
import use_case.get_ratings.GetRatingsOutputBoundary;
import use_case.get_ratings.GetRatingsOutputData;

import java.util.HashMap;
import java.util.List;

public class GetRatingsPresenter implements GetRatingsOutputBoundary {

    private final GetRatingsViewModel getRatingsViewModel;

    private final ViewManagerModel viewManagerModel;

    public GetRatingsPresenter(GetRatingsViewModel getRatingsViewModel, ViewManagerModel viewManagerModel) {
        this.getRatingsViewModel = getRatingsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareGetRatingsView(GetRatingsOutputData getRatingsOutputData) {
        GetRatingsState state = new GetRatingsState();

        HashMap<Movie, Integer> ratings = getRatingsOutputData.getRatings();
        List<Movie> movieList = getRatingsOutputData.getMovieList();

        state.setRatings(ratings);
        state.setMovieList(movieList);

        getRatingsViewModel.setState(state);
        viewManagerModel.firePropertyChanged();

        viewManagerModel.setActiveView(getRatingsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        viewManagerModel.firePropertyChanged();
    }
}
