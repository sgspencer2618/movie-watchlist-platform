package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;


import interface_adapters.add_to_watchlist.AddToWatchlistController;
import interface_adapters.remove_from_watchlist.RemoveFromWatchlistController;
import interface_adapters.search.SearchController;
import interface_adapters.search.SearchState;
import interface_adapters.search.SearchViewModel;

public class SearchView extends DefaultView implements PropertyChangeListener, ActionListener{

    SearchController searchController;

    final JTextField searchInputField = new JTextField(15);
    final JButton searchButton;

    public SearchView(SearchController controller, SearchViewModel searchViewModel, MovieInfoView movieInfoView,
                      AddToWatchlistController addToWatchlistController, RemoveFromWatchlistController removeFromWatchlistController) {
        super(addToWatchlistController, removeFromWatchlistController);
        setBackground(new Color(0, 0, 0));
        this.searchController = controller;
        this.movieInfoView = movieInfoView;
        this.viewModel = searchViewModel;
        searchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Search Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel searchInfo = new LabelTextPanel(new JLabel("Search"), searchInputField);
        searchInfo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        JPanel buttons = new JPanel();
        searchButton = new JButton("SEARCH");
        buttons.add(searchButton, BorderLayout.NORTH);

        searchButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(searchButton)) {
                        SearchState currState = searchViewModel.getState();
                        searchController.execute(currState.getUsername(), currState.getSearchQuery());
                    }
                }
        );

        searchInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SearchState currState = searchViewModel.getState();
                currState.setSearchQuery(searchInputField.getText() + e.getKeyChar());
                System.out.println(searchViewModel.getState().getSearchQuery());
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(searchInfo, BorderLayout.PAGE_START);
        searchInfo.add(buttons, BorderLayout.LINE_END);
    }

    @Override
    public void createWatchlistPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Create a scroll pane to hold the panel list
        this.movieList = viewModel.getState().getMovieList();
        this.ratings = viewModel.getState().getRatings();
        this.watchlist = viewModel.getState().getWatchlist();
        this.scrollPane = new JScrollPane(createPanelList(movieList, ratings, watchlist));
        this.scrollPane.setPreferredSize(DIMENSIONS);
    }


    public void showSearchView(String user) {
        searchController.execute(user, "");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("WOOOOHOOOOO");
        this.UpdateView();
    }

    public String getCurrUser() {
        return viewModel.getState().getUsername();
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

}
