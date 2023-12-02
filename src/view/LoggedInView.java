package view;

import interface_adapters.logged_in.LoggedInState;
import interface_adapters.logged_in.LoggedInViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final WatchlistView watchlistView;
    private final RatingsView ratingsView;
    private final SearchView searchView;

    JTabbedPane tabbedPane;

    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel, WatchlistView watchlistView, RatingsView ratingsView, SearchView searchView) {
        this.loggedInViewModel = loggedInViewModel;
        this.searchView = searchView;
        this.watchlistView = watchlistView;
        this.ratingsView = ratingsView;
        this.loggedInViewModel.addPropertyChangeListener(this);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        tabbedPane = new JTabbedPane();
        //tabbedPane.setSize(800,800);

        tabbedPane.addTab("My Watch List", watchlistView);
        tabbedPane.addTab("My Ratings", ratingsView);
        tabbedPane.addTab("Movie Search", searchView);

        // add property change support to update panes
        watchlistView.addPropertyChangeListener(this);
        searchView.addPropertyChangeListener(this);
        ratingsView.addPropertyChangeListener(this);

        tabbedPane.addChangeListener(e -> {
            System.out.println("Tab: " + tabbedPane.getSelectedIndex());
            if (tabbedPane.getSelectedIndex() == 0) {
                fetchWatchlist();
            }
            else if (tabbedPane.getSelectedIndex() == 1) {
                fetchRatings();
            } else if (tabbedPane.getSelectedIndex() == 2) {
                fetchSearch();
            }
            // Prints the string 3 times if there are 3 tabs etc
        });

        add(tabbedPane);
        setPreferredSize(new Dimension(800,800));
        setVisible(true);

    }

    private void fetchWatchlist() {
        String username = loggedInViewModel.getState().getUsername();
        watchlistView.showWatchlist(username);
        watchlistView.createWatchlistPanel();
        tabbedPane.setSelectedComponent(watchlistView);
    }

    private void fetchRatings() {
        ratingsView.showRatings(loggedInViewModel.getState().getUsername());
        ratingsView.createWatchlistPanel();
        tabbedPane.setSelectedComponent(ratingsView);
    }

    private void fetchSearch() {
        searchView.showSearchView(loggedInViewModel.getState().getUsername());
        searchView.createWatchlistPanel();
        tabbedPane.setSelectedComponent(searchView);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("updateTab")) {
            tabbedPane.setSelectedComponent(searchView);
            this.revalidate();
            this.repaint();
        } else {
            LoggedInState state = (LoggedInState) evt.getNewValue();
            String user = state.getUsername();
            System.out.println("logged in: " + user);
            fetchWatchlist();
        }
    }
}