package interface_adapters.login;

import interface_adapters.get_ratings.GetRatingsState;
import interface_adapters.get_ratings.GetRatingsViewModel;
import interface_adapters.get_watchlist.GetWatchlistState;
import interface_adapters.get_watchlist.GetWatchlistViewModel;
import interface_adapters.logged_in.LoggedInState;
import interface_adapters.logged_in.LoggedInViewModel;
import interface_adapters.ViewManagerModel;
import interface_adapters.search.SearchState;
import interface_adapters.search.SearchViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    private final GetRatingsViewModel getRatingsViewModel;

    private final GetWatchlistViewModel getWatchlistViewModel;

    private final SearchViewModel searchViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel,
                          GetWatchlistViewModel getWatchlistViewModel,
                          GetRatingsViewModel getRatingsViewModel,
                          SearchViewModel searchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.getRatingsViewModel = getRatingsViewModel;
        this.getWatchlistViewModel = getWatchlistViewModel;
        this.searchViewModel = searchViewModel;

    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        String username = response.getUsername();
        LoggedInState loggedInState = loggedInViewModel.getState();
        GetWatchlistState watchlistState = getWatchlistViewModel.getState();
        GetRatingsState ratingsState = getRatingsViewModel.getState();
        SearchState searchState = searchViewModel.getState();

        loggedInState.setUsername(username);
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        watchlistState.setUsername(username);
        this.getWatchlistViewModel.setState(watchlistState);
        this.getWatchlistViewModel.firePropertyChanged();

        ratingsState.setUsername(username);
        this.getRatingsViewModel.setState(ratingsState);
        this.getRatingsViewModel.firePropertyChanged();

        searchState.setUsername(username);
        this.searchViewModel.setState(searchState);
        this.searchViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
