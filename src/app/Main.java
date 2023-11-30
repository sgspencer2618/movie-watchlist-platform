package app;

import data_access.FileUserDataAccessObject;
import data_access.UserRatingAccessObject;
import data_access.WatchlistAccessObject;
import entity.CommonUserFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.get_ratings.GetRatingsViewModel;
import interface_adapters.get_watchlist.GetWatchlistViewModel;
import interface_adapters.logged_in.LoggedInViewModel;
import interface_adapters.login.LoginViewModel;
import interface_adapters.movie_info.MovieInfoController;
import interface_adapters.movie_info.MovieInfoViewModel;
import interface_adapters.signup.SignupViewModel;
import interface_adapters.remove_rating.RemoveRatingViewModel;
import interface_adapters.update_rating.UpdateRatingViewModel;
import use_case.get_watchlist.GetWatchlistDataAccessInterface;
import utility.ApiInterface;
import utility.OMDBCaller;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        GetWatchlistViewModel getWatchlistViewModel = new GetWatchlistViewModel();
        GetRatingsViewModel getRatingsViewModel = new GetRatingsViewModel();
        MovieInfoViewModel movieInfoViewModel = new MovieInfoViewModel();
        RemoveRatingViewModel removeRatingViewModel = new RemoveRatingViewModel();
        UpdateRatingViewModel updateRatingViewModel = new UpdateRatingViewModel();


        //API initializer
        ApiInterface api = new OMDBCaller();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UserRatingAccessObject ratingAccessObject = new UserRatingAccessObject("./userRatings.csv");
        GetWatchlistDataAccessInterface watchlistAccessObject = new WatchlistAccessObject("./watchlist.csv");

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        MovieInfoView movieInfoView = MovieInfoUseCaseFactory.create(api, movieInfoViewModel);
        WatchlistView watchlistView = GetWatchlistUseCaseFactory.create(api, getWatchlistViewModel, viewManagerModel, watchlistAccessObject, ratingAccessObject, movieInfoView);
        RatingsView ratingsView = GetRatingsUseCaseFactory.create(api, getRatingsViewModel, viewManagerModel, ratingAccessObject, watchlistAccessObject, movieInfoView);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel, watchlistView, ratingsView);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setMinimumSize(new Dimension(600,500));
        application.setVisible(true);
    }
}