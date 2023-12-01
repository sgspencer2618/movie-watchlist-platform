package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interface_adapters.movie_info.MovieInfoController;
import interface_adapters.search.SearchController;
import interface_adapters.search.SearchState;
import interface_adapters.search.SearchViewModel;

import entity.User;

public class SearchView extends DefaultView implements PropertyChangeListener, ActionListener{

    SearchController searchController;
    SearchViewModel searchViewModel;
    public final String viewName = "Search";
    private String username;

    final JTextField searchInputField = new JTextField(15);
    final JButton searchButton;

    public SearchView(SearchController controller, SearchViewModel searchViewModel, MovieInfoView movieInfoView) {
        this.searchController = controller;
        this.movieInfoView = movieInfoView;
        this.searchViewModel = searchViewModel;
        this.viewModel = searchViewModel;
        searchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Search Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel searchInfo = new LabelTextPanel(new JLabel("Search"), searchInputField);

        JPanel buttons = new JPanel();
        searchButton = new JButton("SEARCH");
        buttons.add(searchButton);

        searchButton.addActionListener(
            new ActionListener() {
                public void actionPerformed (ActionEvent evt) {
                    if (evt.getSource().equals(searchButton)) {
                        SearchState currState = searchViewModel.getState();

                        searchController.execute(username, currState.getSearchQuery());
                    }
                }
            }
        );

        searchInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SearchState currState = searchViewModel.getState();
                currState.setSearchQuery(searchInputField.getText() + e.getKeyChar());
                searchViewModel.setState(currState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(searchInfo);
        this.add(buttons);
    }

    public void showSearchView(String user) {
        this.username = user;
        searchController.execute(user, "");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchState state = (SearchState) evt.getNewValue();
        this.movieList = state.getMovieList();
        this.ratings = state.getRatings();
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }
}
