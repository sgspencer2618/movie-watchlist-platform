package use_case.search;

import entity.Movie;
import utility.ApiInterface;

import java.util.HashMap;
import java.util.List;

public class SearchInteractor implements SearchHandlerInputBoundary {

    final ApiInterface APICaller;

    final SearchUserDataAccessInterface userDataAccessObject;

    final SearchUserRatingsDataAccessInterface userRatingsDataAccessObject;

    final SearchUserWatchlistDataAccessInterface userWatchlistDataAccessObject;

    final  SearchHandlerOutputBoundary searchPresenter;

public SearchInteractor(ApiInterface APICaller,
                        SearchUserDataAccessInterface userDataAccessInterface,
                        SearchUserRatingsDataAccessInterface userRatingsDataAccessObject,
                        SearchUserWatchlistDataAccessInterface userWatchlistDataAccessObject,
                        SearchHandlerOutputBoundary searchOutputBoundary){
    this.APICaller = APICaller;
    this.userDataAccessObject = userDataAccessInterface;
    this.userRatingsDataAccessObject = userRatingsDataAccessObject;
    this.userWatchlistDataAccessObject = userWatchlistDataAccessObject;
    this.searchPresenter = searchOutputBoundary;
}

public void execute(SearchHandlerInputData searchInputData){
    String currUser = userDataAccessObject.getCurrUser();
    List<Movie> searchResult = APICaller.getSearch(searchInputData.getSearchQuery(), 1);

    HashMap<String, Integer> userRatingsHashmap = new HashMap<>();
    for (Movie movie : searchResult) {
        String imdbID = movie.getImdbID();
        Integer rating = userRatingsDataAccessObject.getUserRating(currUser, imdbID);

        // if no rating, stores rating = -1
        userRatingsHashmap.put(imdbID, rating);
    }

    }

}
