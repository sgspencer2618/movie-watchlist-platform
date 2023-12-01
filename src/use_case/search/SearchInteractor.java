package use_case.search;

import entity.Movie;
import utility.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import data_access.UserRatingAccessObject;
import data_access.WatchlistAccessObject;
import entity.UserRating;

public class SearchInteractor implements SearchHandlerInputBoundary {

    final ApiInterface APICaller;
    final UserRatingAccessObject userRatingAccessObject;
    final WatchlistAccessObject watchlistAccessObject;
    final SearchHandlerOutputBoundary searchPresenter;

public SearchInteractor(ApiInterface APICaller,
                        UserRatingAccessObject userRatingAccessObject,
                        WatchlistAccessObject watchlistAccessObject,
                        SearchHandlerOutputBoundary searchOutputBoundary){
    this.APICaller = APICaller;
    this.userRatingAccessObject = userRatingAccessObject;
    this.watchlistAccessObject = watchlistAccessObject;
    this.searchPresenter = searchOutputBoundary;
}

public void execute(SearchHandlerInputData searchInputData){
    String currUserName = searchInputData.getUser();
    List<Movie> searchResult = APICaller.getSearch(searchInputData.getSearchQuery(), 1);
    List<UserRating> userRatings = new ArrayList<>();
    for (Movie mov : searchResult) {
        if (userRatingAccessObject.userRatingExists(currUserName, mov.getImdbID())) {
            userRatings.add(userRatingAccessObject.getUserRating(currUserName, mov.getImdbID()));
        }
    }
    SearchHandlerOutputData outputData = new SearchHandlerOutputData(searchResult, userRatings, watchlistAccessObject.getWatchlist(currUserName));
    searchPresenter.prepareSuccessView(outputData);
    }

}
