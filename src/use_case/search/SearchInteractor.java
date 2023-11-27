package use_case.search;

import entity.Movie;
import utility.ApiInterface;

import java.util.ArrayList;
import java.util.List;
import entity.UserRating;

public class SearchInteractor implements SearchHandlerInputBoundary {

    final ApiInterface APICaller;
    final SearchHandlerDataAccessInterface searchDataAcess;
    final SearchHandlerOutputBoundary searchPresenter;

public SearchInteractor(ApiInterface APICaller,
                        SearchHandlerDataAccessInterface searchDataAcess,
                        SearchHandlerOutputBoundary searchOutputBoundary){
    this.APICaller = APICaller;
    this.searchDataAcess = searchDataAcess;
    this.searchPresenter = searchOutputBoundary;
}

public void execute(SearchHandlerInputData searchInputData){
    String currUserName = searchInputData.getUser().getUsername();
    List<Movie> searchResult = APICaller.getSearch(searchInputData.getSearchQuery(), 1);
    List<UserRating> userRatings = new ArrayList<>();
    for (Movie mov : searchResult) {
        if (searchDataAcess.userRatingExists(currUserName, mov.getImdbID())) {
            userRatings.add(searchDataAcess.getUserRating(currUserName, mov.getImdbID()));
        }
    }
    SearchHandlerOutputData outputData = new SearchHandlerOutputData(searchResult, userRatings);
    searchPresenter.prepareSuccessView(outputData);
    }

}
