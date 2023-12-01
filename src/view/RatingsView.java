package view;

import entity.Movie;
import entity.UserRating;
import interface_adapters.get_ratings.GetRatingsController;
import interface_adapters.get_ratings.GetRatingsState;
import interface_adapters.get_ratings.GetRatingsViewModel;
import interface_adapters.movie_info.MovieInfoController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class RatingsView extends DefaultView implements PropertyChangeListener {
    private final GetRatingsController getRatingsController;
    public RatingsView(GetRatingsController getRatingsController, GetRatingsViewModel getRatingsViewModel, MovieInfoView movieInfoView) {
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
}