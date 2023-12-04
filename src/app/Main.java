package app;

import data_access.FileUserDataAccessObject;
import data_access.UserRatingAccessObject;
import data_access.WatchlistAccessObject;
import entity.CommonUserFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.add_to_watchlist.AddToWatchlistController;
import interface_adapters.get_ratings.GetRatingsViewModel;
import interface_adapters.get_watchlist.GetWatchlistViewModel;
import interface_adapters.logged_in.LoggedInViewModel;
import interface_adapters.login.LoginViewModel;
import interface_adapters.movie_info.MovieInfoViewModel;
import interface_adapters.remove_from_watchlist.RemoveFromWatchlistController;
import interface_adapters.remove_rating.RemoveRatingController;
import interface_adapters.remove_rating.RemoveRatingViewModel;
import interface_adapters.search.SearchViewModel;
import interface_adapters.signup.SignupViewModel;
import interface_adapters.update_rating.UpdateRatingController;
import interface_adapters.update_rating.UpdateRatingViewModel;
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
        JFrame application = new JFrame("Watchlist Movie Platform");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);



        // ViewModels
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        GetWatchlistViewModel getWatchlistViewModel = new GetWatchlistViewModel();
        GetRatingsViewModel getRatingsViewModel = new GetRatingsViewModel();
        UpdateRatingViewModel updateRatingViewModel = new UpdateRatingViewModel();
        RemoveRatingViewModel removeRatingViewModel = new RemoveRatingViewModel();
        MovieInfoViewModel movieInfoViewModel = new MovieInfoViewModel();
        SearchViewModel searchViewModel = new SearchViewModel();


        //API initializer
        ApiInterface api = new OMDBCaller();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UserRatingAccessObject ratingAccessObject = new UserRatingAccessObject("./userRatings.csv");
        WatchlistAccessObject  watchlistAccessObject = new WatchlistAccessObject("./watchlist.csv");

        // Controllers
        AddToWatchlistController addToWatchlistController = AddToWatchlistUseCaseFactory.createAddToWatchlistUseCase(watchlistAccessObject);
        RemoveFromWatchlistController removeFromWatchlistController = RemoveFromWatchlistUseCaseFactory.createRemoveFromWatchlistUseCase(watchlistAccessObject);
        UpdateRatingController updateRatingController = UpdateRatingUseCaseFactory.createUpdateRatingUseCase(updateRatingViewModel, ratingAccessObject);
        RemoveRatingController removeRatingController = RemoveRatingUseCaseFactory.createRemoveRatingUseCase(removeRatingViewModel, ratingAccessObject);

        // Creating signup view
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        assert signupView != null;
        views.add(signupView, signupView.viewName);

        // Creating login view
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, getWatchlistViewModel, getRatingsViewModel, searchViewModel);
        assert loginView != null;
        views.add(loginView, loginView.viewName);


        MovieInfoView movieInfoView = MovieInfoUseCaseFactory.create(api, movieInfoViewModel);
        WatchlistView watchlistView = GetWatchlistUseCaseFactory.create(api, getWatchlistViewModel, watchlistAccessObject, ratingAccessObject, movieInfoView, addToWatchlistController, removeFromWatchlistController, updateRatingController, removeRatingController);
        RatingsView ratingsView = GetRatingsUseCaseFactory.create(api, getRatingsViewModel, ratingAccessObject, watchlistAccessObject, movieInfoView, addToWatchlistController, removeFromWatchlistController, updateRatingController, removeRatingController);
        SearchView searchView =  SearchUseCaseFactory.create(api, searchViewModel, ratingAccessObject, watchlistAccessObject, movieInfoView, addToWatchlistController, removeFromWatchlistController, updateRatingController, removeRatingController);

        // Creating logged in view
        LoggedInView loggedInView = new LoggedInView(loggedInViewModel, watchlistView, ratingsView, searchView);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setMinimumSize(new Dimension(600,500));
        application.setVisible(true);
    }
}