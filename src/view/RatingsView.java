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
    private final MovieInfoController movieInfoController;
    private final GetRatingsViewModel getRatingsViewModel;
    private final Dimension DIMENSIONS = new Dimension(350,275);
    private List<Movie> movieList;
    private List<UserRating> ratings;
    private JScrollPane scrollPane;
    private JPanel panelList;


    public RatingsView(GetRatingsController getRatingsController, MovieInfoController movieInfoController, GetRatingsViewModel getRatingsViewModel) {
        this.getRatingsController = getRatingsController;
        this.movieInfoController = movieInfoController;
        this.getRatingsViewModel = getRatingsViewModel;
        getRatingsViewModel.addPropertyChangeListener(this);
    }

    public void createRatingsPanel() {;
        setLayout(new BorderLayout());

        // Create a scroll pane to hold the panel list
        movieList = getRatingsViewModel.getState().getMovieList();
        if (movieList.isEmpty()) {
            System.out.println("Movie List is empty");
        }
        ratings = getRatingsViewModel.getState().getRatings();
        scrollPane = new JScrollPane(createPanelList(movieList, ratings));
        scrollPane.setPreferredSize(DIMENSIONS);

        add(scrollPane, BorderLayout.CENTER);

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
    }
}