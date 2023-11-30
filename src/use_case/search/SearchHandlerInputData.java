package use_case.search;

import entity.User;

public class SearchHandlerInputData {

    final private String searchQuery;
    final private String username;
    public SearchHandlerInputData(String searchQuery, String username) {
        this.searchQuery = searchQuery;
        this.username = username;
    }

    String getSearchQuery(){return searchQuery;}
    String getUser() {return username;}

}
