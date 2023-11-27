package view;

import entity.Movie;
import entity.UserRating;
import interface_adapters.logged_in.LoggedInState;
import interface_adapters.logged_in.LoggedInViewModel;
import interface_adapters.movie_info.MovieInfoController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class LoggedInView extends DefaultView implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final WatchlistView watchlistView;
    private final RatingsView ratingsView;

    private final MovieInfoController movieInfoController;
    private List<Movie> movieList;
    private List<UserRating> ratings;
    private JScrollPane scrollPane;
    private JPanel panelList;
    private final Dimension DIMENSIONS = new Dimension(350,275);
    private String user;


    JLabel username;
    JTabbedPane tabbedPane;
    //create new watchlist tab
    JPanel mywatchlist;

    //create new ratings tab
    JPanel myratings;

    //create new search tab
    JPanel moviesearch;

    //final JButton logOut;

    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel, WatchlistView watchlistView, RatingsView ratingsView, MovieInfoController movieInfoController) {
        this.loggedInViewModel = loggedInViewModel;
        this.watchlistView = watchlistView;
        this.ratingsView = ratingsView;
        this.movieInfoController = movieInfoController;
        this.loggedInViewModel.addPropertyChangeListener(this);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        tabbedPane = new JTabbedPane();
        tabbedPane.setSize(800,800);
        //create new watchlist tab
        mywatchlist = new JPanel();
        mywatchlist.setLayout(new BorderLayout());

////        JPanel searchpanel = new JPanel(new BorderLayout());
////        JButton search = new JButton("Search");
//
//        searchpanel.add(new TextField("", 30));
//        searchpanel.add(search, BorderLayout.AFTER_LINE_ENDS);
//
//        mywatchlist.add(searchpanel, BorderLayout.PAGE_START);

        LoggedInState state = loggedInViewModel.getState();
        user = state.getUsername();

//        mywatchlist.add(watchlistView);

        //create new ratings tab
        myratings = new JPanel();
        myratings.setLayout(new BorderLayout());

        //create new search tab
        moviesearch = new JPanel();

        tabbedPane.addTab("My Watch List", mywatchlist);
        tabbedPane.addTab("My Ratings", myratings);
        tabbedPane.addTab("Movie Search", moviesearch);

        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                System.out.println("Tab: " + tabbedPane.getSelectedIndex());
                if (tabbedPane.getSelectedIndex() == 0) {
                    fetchWatchlist(user);
                }
                else if (tabbedPane.getSelectedIndex() == 1) {
                    fetchRatings(user);
                }
                // Prints the string 3 times if there are 3 tabs etc
            }
        });

        add(tabbedPane);
        setPreferredSize(new Dimension(800,800));
        setVisible(true);

    }

    private void fetchWatchlist(String user) {
        watchlistView.showWatchlist(user);
        watchlistView.createWatchlistPanel();
        mywatchlist.add(watchlistView);
    }

    private void fetchRatings(String user) {
        ratingsView.showRatings(user);
        ratingsView.createRatingsPanel();
        myratings.add(ratingsView);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        String user = state.getUsername();
        System.out.println("logged in: " + user);
        fetchWatchlist(user);
        //fetchRatings(user);
    }
}