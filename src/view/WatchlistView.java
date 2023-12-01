package view;

import interface_adapters.add_to_watchlist.AddToWatchlistController;
import interface_adapters.get_watchlist.GetWatchlistController;
import interface_adapters.get_watchlist.GetWatchlistState;
import interface_adapters.get_watchlist.GetWatchlistViewModel;
import interface_adapters.remove_from_watchlist.RemoveFromWatchlistController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class WatchlistView extends DefaultView implements PropertyChangeListener {
    private GetWatchlistController getWatchlistController;


    public WatchlistView(GetWatchlistController getWatchlistController, GetWatchlistViewModel getWatchlistViewModel, MovieInfoView movieInfoView,
                         AddToWatchlistController addToWatchlistController, RemoveFromWatchlistController removeFromWatchlistController) {
        super(addToWatchlistController, removeFromWatchlistController);
        this.getWatchlistController = getWatchlistController;
        this.movieInfoView = movieInfoView;
        this.viewModel = getWatchlistViewModel;
        getWatchlistViewModel.addPropertyChangeListener(this);
    }

    private void UpdateView(GetWatchlistState state) {
        this.movieList = state.getMovieList();
        this.ratings = state.getRatings();
    }

    public void showWatchlist(String user) {
        getWatchlistController.execute(user);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GetWatchlistState state = (GetWatchlistState) evt.getNewValue();
        UpdateView(state);
    }

    public String getCurrUser() {
        return viewModel.getState().getUsername();
    }
}
