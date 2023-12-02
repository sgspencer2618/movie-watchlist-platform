package view;

import interface_adapters.add_to_watchlist.AddToWatchlistController;
import interface_adapters.get_ratings.GetRatingsController;
import interface_adapters.get_ratings.GetRatingsState;
import interface_adapters.get_ratings.GetRatingsViewModel;
import interface_adapters.remove_from_watchlist.RemoveFromWatchlistController;
import interface_adapters.remove_rating.RemoveRatingController;
import interface_adapters.update_rating.UpdateRatingController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RatingsView extends DefaultView implements PropertyChangeListener {
    private final GetRatingsController getRatingsController;
    public RatingsView(GetRatingsController getRatingsController, GetRatingsViewModel getRatingsViewModel, MovieInfoView movieInfoView,
                       AddToWatchlistController addToWatchlistController, RemoveFromWatchlistController removeFromWatchlistController, UpdateRatingController updateRatingController, RemoveRatingController removeRatingController) {
        super(addToWatchlistController, removeFromWatchlistController, updateRatingController, removeRatingController); // Call the constructor of the DefaultView class
        this.getRatingsController = getRatingsController;
        this.viewModel = getRatingsViewModel;
        this.movieInfoView = movieInfoView;
        getRatingsViewModel.addPropertyChangeListener(this);
    }



    private void UpdateView(GetRatingsState state) {
        this.movieList = state.getMovieList();
        this.ratings = state.getRatings();
    }

    public void showRatings(String user) {
        getRatingsController.execute(user);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GetRatingsState state = (GetRatingsState) evt.getNewValue();
        UpdateView(state);
    }

    public String getCurrUser() {
        return viewModel.getState().getUsername();
    }
}