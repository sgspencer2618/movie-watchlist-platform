package interface_adapters.get_watchlist;

import entity.Movie;
import entity.UserRating;
import entity.Watchlist;
import use_case.get_watchlist.GetWatchlistOutputBoundary;
import use_case.get_watchlist.GetWatchlistOutputData;

import java.util.List;

public class GetWatchlistPresenter implements GetWatchlistOutputBoundary {

    private final GetWatchlistViewModel getWatchlistViewModel;

    public GetWatchlistPresenter(GetWatchlistViewModel getWatchlistViewModel) {
        this.getWatchlistViewModel = getWatchlistViewModel;
    }

    @Override
    public void prepareGetWatchlistView(GetWatchlistOutputData getWatchlistOutputData) {
        GetWatchlistState state = getWatchlistViewModel.getState();

        Watchlist watchlist = getWatchlistOutputData.getWatchlist();
        List<UserRating> ratings = getWatchlistOutputData.getRatings();
        List<Movie> movieList = getWatchlistOutputData.getMovieList();

        state.setWatchlist(watchlist);
        state.setRatings(ratings);
        state.setMovieList(movieList);

        getWatchlistViewModel.setState(state);
        getWatchlistViewModel.firePropertyChanged();
    }
}
