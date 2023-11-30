package interface_adapters.search;

import use_case.search.SearchHandlerInputData;
import use_case.search.SearchHandlerInputBoundary;
import entity.User;

public class SearchController {

    final SearchHandlerInputBoundary searchInteractor;

    public SearchController(SearchHandlerInputBoundary searchInteractor) {
        this.searchInteractor = searchInteractor;
    }

    public void execute(String username, String searchQuery){
        SearchHandlerInputData inputData = new SearchHandlerInputData(searchQuery, username);

        searchInteractor.execute(inputData);
    }
}
