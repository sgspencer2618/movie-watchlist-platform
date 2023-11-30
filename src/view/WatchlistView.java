package view;

import entity.Movie;
import entity.UserRating;
import interface_adapters.get_watchlist.GetWatchlistController;
import interface_adapters.get_watchlist.GetWatchlistState;
import interface_adapters.get_watchlist.GetWatchlistViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class WatchlistView extends DefaultView implements PropertyChangeListener {
    private GetWatchlistController getWatchlistController;
    private GetWatchlistViewModel getWatchlistViewModel;
    private final Dimension DIMENSIONS = new Dimension(350,275);
    private List<Movie> movieList;
    private List<UserRating> ratings;
    private JScrollPane scrollPane;
    private JPanel panelList;

    public WatchlistView(GetWatchlistController getWatchlistController, GetWatchlistViewModel getWatchlistViewModel, MovieInfoView movieInfoView) {
        this.getWatchlistController = getWatchlistController;
        this.movieInfoView = movieInfoView;
        this.getWatchlistViewModel = getWatchlistViewModel;
        getWatchlistViewModel.addPropertyChangeListener(this);
    }

    public void createWatchlistPanel() {;
        setLayout(new BorderLayout());

        // Create a scroll pane to hold the panel list
        movieList = getWatchlistViewModel.getState().getMovieList();
        ratings = getWatchlistViewModel.getState().getRatings();
        scrollPane = new JScrollPane(createPanelList(movieList, ratings));
        scrollPane.setPreferredSize(DIMENSIONS);

        add(scrollPane, BorderLayout.CENTER);
    }

    public JPanel createPanelList(List<Movie> movieList) {
        panelList = new JPanel(); // Initialize the panelList field
        panelList.setLayout(new BoxLayout(panelList, BoxLayout.Y_AXIS));

        // Add some sample data
        if (movieList != null) {
            for (Movie movie : movieList) {
                panelList.add(createClickablePanel(movie));
            }
        }

        return panelList;
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
        //UpdateView(state);
    }
}
