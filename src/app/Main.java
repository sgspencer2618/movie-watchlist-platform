package app;

import data_access.FileUserDataAccessObject;
import data_access.MapUserDataAccessObject;
import data_access.UserRatingAccessObject;
import data_access.WatchlistAccessObject;
import data_store.UserDBInterface;
import data_store.UserDBMap;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.get_ratings.GetRatingsViewModel;
import interface_adapters.get_watchlist.GetWatchlistViewModel;
import interface_adapters.logged_in.LoggedInViewModel;
import interface_adapters.login.LoginViewModel;
import interface_adapters.movie_info.MovieInfoViewModel;
import interface_adapters.signup.SignupViewModel;
import use_case.get_watchlist.GetWatchlistDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import utility.ApiInterface;
import utility.OMDBCaller;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Main {
    private static ApiInterface api;
    private static FileUserDataAccessObject fileUserDataAccessObject;

    private static MapUserDataAccessObject mapUserDataAccessObject;

    private static SignupUserDataAccessInterface signupUserDAO;
    private static LoginUserDataAccessInterface loginUserDAO;

    public static void main(String[] args) {
        appInit();
        initUI();
    }

    public static void initUI() {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        GetWatchlistViewModel getWatchlistViewModel = new GetWatchlistViewModel();
        GetRatingsViewModel getRatingsViewModel = new GetRatingsViewModel();
        MovieInfoViewModel movieInfoViewModel = new MovieInfoViewModel();

        UserRatingAccessObject ratingAccessObject = new UserRatingAccessObject();
        GetWatchlistDataAccessInterface watchlistAccessObject = new WatchlistAccessObject();

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, signupUserDAO);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, loginUserDAO);
        views.add(loginView, loginView.viewName);

        WatchlistView watchlistView = GetWatchlistUseCaseFactory.create(api, getWatchlistViewModel, viewManagerModel, watchlistAccessObject, ratingAccessObject, movieInfoViewModel);
        RatingsView ratingsView = GetRatingsUseCaseFactory.create(api, getRatingsViewModel, viewManagerModel, ratingAccessObject, watchlistAccessObject, movieInfoViewModel);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel, watchlistView, ratingsView);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                appShutdown();
            }
        });

        application.pack();
        application.setVisible(true);
    }

    public static void appInit() {
        UserFactory userFactory = new CommonUserFactory();
        UserDBInterface userDBMap = new UserDBMap(userFactory);

        //Movie API initializer
        api = new OMDBCaller();

        //create File DAO
        try {
            fileUserDataAccessObject = new FileUserDataAccessObject("./users.csv", userFactory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //create Map DAO
        mapUserDataAccessObject = new MapUserDataAccessObject(userDBMap, userFactory);

        //assign File DAOs
        if(true) {
            loginUserDAO = fileUserDataAccessObject;
            signupUserDAO = fileUserDataAccessObject;
        }

        //assign Map DAOs
        if(false) {
            loginUserDAO = mapUserDataAccessObject;
            signupUserDAO = mapUserDataAccessObject;
        }
    }

    public static void appShutdown() {
        System.out.println("Shutting down");
    }
}