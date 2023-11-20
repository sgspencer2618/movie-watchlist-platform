package interface_adapters.get_watchlist;

import entity.Movie;
import entity.Watchlist;
import interface_adapters.ViewManagerModel;
import use_case.get_watchlist.GetWatchlistOutputBoundary;
import use_case.get_watchlist.GetWatchlistOutputData;

import java.util.HashMap;

public class GetWatchlistPresenter implements GetWatchlistOutputBoundary {

    private final GetWatchlistViewModel getWatchlistViewModel;
    private final ViewManagerModel viewManagerModel;

    public GetWatchlistPresenter(GetWatchlistViewModel getWatchlistViewModel, ViewManagerModel viewManagerModel) {
        this.getWatchlistViewModel = getWatchlistViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareGetWatchlistView(GetWatchlistOutputData getWatchlistOutputData) {
        GetWatchlistState state = new GetWatchlistState();

        Watchlist watchlist = getWatchlistOutputData.getWatchlist();
        HashMap<Movie, Integer> ratings = getWatchlistOutputData.getRatings();

        state.setWatchlist(watchlist);
        state.setRatings(ratings);

        this.getWatchlistViewModel.setState(state);
        viewManagerModel.firePropertyChanged();

        viewManagerModel.setActiveView(getWatchlistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();


        //TODO: add ViewManagerModel fire property change calls
    }
}
