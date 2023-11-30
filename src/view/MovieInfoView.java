package view;

import interface_adapters.movie_info.MovieInfoController;
import interface_adapters.movie_info.MovieInfoState;
import interface_adapters.movie_info.MovieInfoViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MovieInfoView implements PropertyChangeListener {

    private static final int WINDOW_WIDTH = 600, WINDOW_HEIGHT = 327;
    private final MovieInfoController controller;
    private final MovieInfoViewModel viewmodel;

    private JFrame frame;
    private JLabel title, poster, info, talent, imdbRating, rottenTomatoesRating, metacriticRating;
    private JTextArea description;

    public MovieInfoView(MovieInfoController controller, MovieInfoViewModel viewmodel) {
        this.controller = controller;
        this.viewmodel = viewmodel;
        viewmodel.addPropertyChangeListener(this);

        // create window
        createWindow();
    }

    private void createWindow() {
        frame = new JFrame("Detailed Movie Info");

        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        title = new JLabel();
        title.setFont(new Font("Sans", Font.BOLD, 22));
        title.setLocation(170, 5);
        title.setSize(600, 30);
        frame.add(title);

        poster = new JLabel();
        poster.setHorizontalAlignment(JLabel.CENTER);
        poster.setSize(150, 221);
        poster.setLocation(10, 10);
        frame.add(poster);

        description = new JTextArea();
        description.setFont(new Font("Sans", Font.PLAIN, 14));
        description.setBackground(frame.getBackground());
        description.setSize(420, 192);
        description.setLocation(170, 40);
        description.setWrapStyleWord(true);
        description.setFocusable(false);
        description.setEditable(false);
        description.setLineWrap(true);

        // add scroll bar to plot area if necessary
        JScrollPane scroll = new JScrollPane(description, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(2);
        scroll.setSize(420, 120);
        scroll.setLocation(170, 70);
        frame.add(scroll);

        // four dividing lines on the GUI
        JLabel d1 = new JLabel();
        d1.setOpaque(true);
        d1.setBackground(Color.LIGHT_GRAY);
        d1.setSize(420, 1);
        d1.setLocation(170, 37);

        JLabel d2 = new JLabel();
        d2.setOpaque(true);
        d2.setBackground(Color.LIGHT_GRAY);
        d2.setSize(new Dimension(420, 1));
        d2.setLocation(new Point(170, 63));

        JLabel d3 = new JLabel();
        d3.setOpaque(true);
        d3.setBackground(Color.LIGHT_GRAY);
        d3.setSize(420, 1);
        d3.setLocation(170, 203);

        JLabel d4 = new JLabel();
        d4.setOpaque(true);
        d4.setBackground(Color.LIGHT_GRAY);
        d4.setSize(420, 1);
        d4.setLocation(170, 230);

        frame.add(d1);
        frame.add(d2);
        frame.add(d3);
        frame.add(d4);

        info = new JLabel();
        info.setFont(new Font("Sans", Font.BOLD, 14));
        info.setLocation(170, 40);
        info.setSize(420, 20);
        frame.add(info);

        talent = new JLabel();
        talent.setFont(new Font("Sans", Font.BOLD, 14));
        talent.setLocation(170, 206);
        talent.setSize(420, 20);
        frame.add(talent);

        JPanel ratingPanel = new JPanel();
        ratingPanel.setBackground(Color.WHITE);
        ratingPanel.setLocation(0, 241);
        ratingPanel.setSize(600, 100);
        ratingPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // get image icons for imdb, rotten tomatoes, and metacritic
        Image scaledImdb = null, scaledRottenTomatoes = null, scaledMetacritic = null;

        try {
            BufferedImage imdb = ImageIO.read(new File("assets/icons/imdb.png"));
            scaledImdb = imdb.getScaledInstance(32, 32, Image.SCALE_SMOOTH);

            BufferedImage rottenTomatoes = ImageIO.read(new File("assets/icons/rottenTomatoes.png"));
            scaledRottenTomatoes = rottenTomatoes.getScaledInstance(32, 32, Image.SCALE_SMOOTH);

            BufferedImage metacritic = ImageIO.read(new File("assets/icons/metacritic.png"));
            scaledMetacritic = metacritic.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        } catch(IOException e) {
            e.printStackTrace();
        }

        JLabel imdbIcon = new JLabel(new ImageIcon(scaledImdb));
        imdbIcon.setSize(32, 32);
        imdbIcon.setLocation(10, 251);

        imdbRating = new JLabel();
        imdbRating.setFont(new Font("Sans", Font.BOLD, 24));
        ratingPanel.add(imdbIcon);
        ratingPanel.add(imdbRating);

        JSeparator s1 = new JSeparator();
        s1.setOrientation(JSeparator.VERTICAL);
        s1.setPreferredSize(new Dimension(50, 0));
        ratingPanel.add(s1);

        JLabel rottenTomatoesIcon = new JLabel(new ImageIcon(scaledRottenTomatoes));
        rottenTomatoesIcon.setSize(32, 32);
        rottenTomatoesIcon.setLocation(10, 251);

        rottenTomatoesRating = new JLabel();
        rottenTomatoesRating.setFont(new Font("Sans", Font.BOLD, 24));
        ratingPanel.add(rottenTomatoesIcon);
        ratingPanel.add(rottenTomatoesRating);

        JSeparator s2 = new JSeparator();
        s2.setOrientation(JSeparator.VERTICAL);
        s2.setPreferredSize(new Dimension(50, 0));
        ratingPanel.add(s2);

        JLabel metacriticIcon = new JLabel(new ImageIcon(scaledMetacritic));
        metacriticIcon.setSize(32, 32);
        metacriticIcon.setLocation(10, 251);

        metacriticRating = new JLabel();
        metacriticRating.setFont(new Font("Sans", Font.BOLD, 24));
        ratingPanel.add(metacriticIcon);
        ratingPanel.add(metacriticRating);

        frame.add(ratingPanel);
    }

    private void updateView(MovieInfoState state) {
        title.setText(state.getTitleText());
        description.setText(state.getSummaryText());
        info.setText(state.getInfoText());
        talent.setText(state.getTalentText());
        imdbRating.setText(state.getImdbScore());
        rottenTomatoesRating.setText(state.getRottenTomatoesScore());
        metacriticRating.setText(state.getMetacriticScore());

        poster.setIcon(null);
        frame.repaint();
        frame.setVisible(true);

        // update poster
        try {
            URL url = new URL(state.getPosterURL());
            BufferedImage pImg = ImageIO.read(url);
            Image scaled = pImg.getScaledInstance(150, 221, Image.SCALE_SMOOTH);
            poster.setIcon(new ImageIcon(scaled));
        } catch (Exception e) {
            poster.setOpaque(true);
            poster.setBackground(Color.LIGHT_GRAY);
            poster.setText("No Poster");
            e.printStackTrace();
        }

        frame.repaint();
    }

    public void showMovie(String imdbID) {
        controller.execute(imdbID);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        MovieInfoState state = (MovieInfoState) evt.getNewValue();
        updateView(state);
    }
}
