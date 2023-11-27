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

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

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
        String user = state.getUsername();

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
                // Prints the string 3 times if there are 3 tabs etc
            }
        });

        add(tabbedPane);
        setPreferredSize(new Dimension(800,800));
        setVisible(true);

    }

    public void createWatchlistPanel() {;
        setLayout(new BorderLayout());

        // Create a scroll pane to hold the panel list
        movieList = loggedInViewModel.getState().getMovieList();
        ratings = loggedInViewModel.getState().getRatings();
        scrollPane = new JScrollPane(createPanelList(movieList));
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


    public JPanel createClickablePanel(Movie movie) {
        JPanel panel = new JPanel();
        //panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBorder(new EmptyBorder(0, 10, 0, 10));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        panel.setLayout(new BorderLayout(20,0));

        //placeholder url to test image path
        String path = movie.getPosterURL();
        URL url;
        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        try {
            BufferedImage image = ImageIO.read(url);
            if (image != null) {

                Image scaledimage = image.getScaledInstance(60, 90,
                        Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(scaledimage);
                JLabel imagelabel = new JLabel(imageIcon);

                panel.add(imagelabel, BorderLayout.LINE_START);

            } else {
                // Handle the case where the image couldn't be loaded
                JLabel errorLabel = new JLabel("Error loading image");
                panel.add(errorLabel, BorderLayout.LINE_START);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JLabel label = new JLabel(movie.getTitle());
        panel.add(label, BorderLayout.CENTER);

        // Create the dropdown (combobox) for ratings
        String[] ratingOptions = {"1", "2", "3", "4", "5", "-"};
        JComboBox<String> ratingsDropdown = new JComboBox<>(ratingOptions);
        for (UserRating rating: ratings) {
            if (rating.getMovieId() == movie.getImdbID()) {
                ratingsDropdown.setSelectedItem(rating.getRating());
            }
        }

        JPanel controlsubpanel = new JPanel();
        controlsubpanel.setLayout(new BorderLayout(20,0));
        JButton add = new JButton("+");
        add.setPreferredSize(new Dimension(40,40));
        controlsubpanel.setBorder(new EmptyBorder(30, 0, 30, 0));
        add.setPreferredSize(new Dimension(50,50));
        add.setFont(new Font("Arial", Font.PLAIN, 20)); // adjust font size

        controlsubpanel.add(add, BorderLayout.CENTER);
        controlsubpanel.add(ratingsDropdown, BorderLayout.LINE_END);
        panel.add(controlsubpanel, BorderLayout.LINE_END);

        // Add a click listener to the panel
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //PLACEHOLDER - to be replaced with showing the information pane of the movie
                movieInfoController.execute(movie.getImdbID());
            }
        });

        // Defines normal and hover colors
        Color normalColor = panel.getBackground();
        Color hoverColor = new Color(220, 220, 220);

        // Add MouseListener for hover effect
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(normalColor);
            }
        });

        return panel;
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
        fetchRatings(user);
    }
}