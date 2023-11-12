package GUIPrototype;

import javax.swing.*;
import java.awt.*;

public class MovieGUI {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("TabbedPaneFrame");
        JTabbedPane tabby = new JTabbedPane();

        //create new watchlist tab
        JPanel mywatchlist = new JPanel();

        //create new ratings tab
        JPanel myratings = new JPanel();

        //create new search tab
        JPanel moviesearch = new JPanel();
        moviesearch.setLayout(new BorderLayout());

        //create a new panel to hold the search bar and search button
        JPanel searchpanel = new JPanel(new BorderLayout());
        JButton search = new JButton("Search");

        //add the search bar and button to the search panel
        searchpanel.add(new TextField("", 30));
        searchpanel.add(search, BorderLayout.AFTER_LINE_ENDS);

        //creates a GUIPrototype.ClickablePanelList instance with the specified dimensions
        String[] mockmovies = {"movie1", "movie2", "movie3", "movie4", "movie5", "movie6", "movie7"};
        ClickablePanelList movielistpanel = new ClickablePanelList(new Dimension(350,275), mockmovies);
        movielistpanel.createPanelList(mockmovies);

        //add the search and movie panel to the frame
        moviesearch.add(searchpanel, BorderLayout.PAGE_START);
        moviesearch.add(movielistpanel, BorderLayout.CENTER);

        //add all the tabs to the tabbed pane
        tabby.addTab("My Watch List", mywatchlist);
        tabby.addTab("My Ratings", myratings);
        tabby.addTab("Movie Search", moviesearch);

        frame.getContentPane().add(tabby);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible(true);


    }
}