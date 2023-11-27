package use_case.search;

import entity.Movie;
import utility.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import data_access.UserRatingAccessObject;
import entity.UserRating;

public class SearchInteractor implements SearchHandlerInputBoundary {

    final ApiInterface APICaller;
    final UserRatingAccessObject userRatingAccess;
    final SearchHandlerOutputBoundary searchPresenter;

public SearchInteractor(ApiInterface APICaller,
                        UserRatingAccessObject userRatingAccess,
                        SearchHandlerOutputBoundary searchOutputBoundary){
    this.APICaller = APICaller;
    this.userRatingAccess = userRatingAccess;
    this.searchPresenter = searchOutputBoundary;
}

public void execute(SearchHandlerInputData searchInputData){
    String currUserName = searchInputData.getUser().getUsername();
    List<Movie> searchResult = APICaller.getSearch(searchInputData.getSearchQuery(), 1);
    List<UserRating> userRatings = new ArrayList<>();
    for (Movie mov : searchResult) {
        if (userRatingAccess.userRatingExists(currUserName, mov.getImdbID())) {
            userRatings.add(userRatingAccess.getUserRating(currUserName, mov.getImdbID()));
        }
    }
    SearchHandlerOutputData outputData = new SearchHandlerOutputData(searchResult, userRatings);
    searchPresenter.prepareSuccessView(outputData);
    }

}
