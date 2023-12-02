package view;

import entity.Movie;
import entity.UserRating;
import entity.Watchlist;
import interface_adapters.ViewModel;
import interface_adapters.add_to_watchlist.AddToWatchlistController;
import interface_adapters.remove_from_watchlist.RemoveFromWatchlistController;
import interface_adapters.remove_rating.RemoveRatingController;
import interface_adapters.update_rating.UpdateRatingController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class DefaultView extends JPanel {
    public MovieInfoView movieInfoView;
    public ViewModel viewModel;
    public final Dimension DIMENSIONS = new Dimension(250,275);
    public List<Movie> movieList;
    public List<UserRating> ratings;
    public Watchlist watchlist;
    public JScrollPane scrollPane;

    private final AddToWatchlistController addToWatchlistController;
    private final RemoveFromWatchlistController removeFromWatchlistController;
    private final UpdateRatingController updateRatingController;
    private final RemoveRatingController removeRatingController;

    public DefaultView(AddToWatchlistController addToWatchlistController, RemoveFromWatchlistController removeFromWatchlistController, UpdateRatingController updateRatingController, RemoveRatingController removeRatingController) {
        setBackground(new Color(0, 0, 0));
        this.removeFromWatchlistController = removeFromWatchlistController;
        this.addToWatchlistController = addToWatchlistController;
        this.updateRatingController = updateRatingController;
        this.removeRatingController = removeRatingController;
    }

    public void createWatchlistPanel() {
        removeAll();
        setLayout(new BorderLayout());

        // Create a scroll pane to hold the panel list
        this.movieList = viewModel.getState().getMovieList();
        this.ratings = viewModel.getState().getRatings();
        this.watchlist = viewModel.getState().getWatchlist();
        this.scrollPane = new JScrollPane(createPanelList(movieList, ratings, watchlist));
        this.scrollPane.setPreferredSize(DIMENSIONS);

        add(scrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void UpdateView() {
        this.movieList = viewModel.getState().getMovieList();
        this.ratings = viewModel.getState().getRatings();
        if (this.scrollPane != null) {
            this.remove(1);
        }
        this.scrollPane = new JScrollPane(createPanelList(movieList, ratings, watchlist));
        this.scrollPane.setPreferredSize(DIMENSIONS);
        this.add(scrollPane);
        this.revalidate();
        this.repaint();
        this.firePropertyChanged();
    }

    public JPanel createPanelList(List<Movie> movieList, List<UserRating> ratings, Watchlist watchlist) {
        JPanel panelList = new JPanel(); // Initialize the panelList field
        panelList.setLayout(new BoxLayout(panelList, BoxLayout.Y_AXIS));

        this.ratings = ratings;

        // Add some sample data
        if (movieList != null) {
            for (Movie movie : movieList) {
                boolean inWatchlist = watchlist.getMovieIDs().contains(movie.getImdbID());
                panelList.add(createClickablePanel(movie, inWatchlist));
            }
        }

        return panelList;
    }

    public JPanel createClickablePanel(Movie movie, boolean inWatchlist) {
        JPanel panel = new JPanel();
        //panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBorder(new EmptyBorder(0, 10, 0, 10));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        panel.setLayout(new BorderLayout(20,0));

        //placeholder url to test image path
        String path = movie.getPosterURL();
        URL url = null;
        try {
            System.out.println(path);
            url = new URL(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = null;
            if(url != null) image = ImageIO.read(url);

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
        ArrayList<String> OPTIONS = new ArrayList<>();
        OPTIONS.add("-");
        OPTIONS.add("1");
        OPTIONS.add("2");
        OPTIONS.add("3");
        OPTIONS.add("4");
        OPTIONS.add("5");
        String[] ratingOptions = {"-","1", "2", "3", "4", "5"};
        JComboBox<String> ratingsDropdown = new JComboBox<>(ratingOptions);
        for (UserRating rating: ratings) {
            if (rating.getMovieId().equals(movie.getImdbID())) {
                int index = OPTIONS.indexOf(String.valueOf(rating.getRating()));
                ratingsDropdown.setSelectedIndex(index);
            }
        }

        ratingsDropdown.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    String item = (String) e.getItem();

                    if(item.equals("-")) {
                        removeRatingController.execute(getCurrUser(), movie.getImdbID());
                    } else {
                        updateRatingController.execute(Integer.parseInt(item), getCurrUser(), movie.getImdbID());
                    }
                }
            }
        });

        JPanel controlsubpanel = new JPanel();
        controlsubpanel.setLayout(new BorderLayout(20,0));
        JButton add;
        ImageIcon checkIcon = new ImageIcon("assets/icons/checkmark.png");
        ImageIcon plusIcon = new ImageIcon("assets/icons/plus.png");
        if(inWatchlist){
            add = new JButton(checkIcon);
        }
        else{
            add = new JButton(plusIcon);
        }
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
                movieInfoView.showMovie(movie.getImdbID());
            }
        });

        // Add a click listener to the add button
        add.addActionListener(e -> {
            String user = getCurrUser();
            if (add.getIcon().equals(plusIcon)) {
                addToWatchlistController.execute(movie, user);
                add.setIcon(checkIcon);
            } else {
                removeFromWatchlistController.execute(movie, user);
                add.setIcon(plusIcon);
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
                controlsubpanel.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(normalColor);
                controlsubpanel.setBackground(normalColor);
            }
        });

        return panel;
    }

    abstract String getCurrUser();


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {
        support.firePropertyChange("updateTab", null, this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
