package use_case.search;

import entity.User;

public class SearchHandlerInputData {

    final private String searchQuery;
    final private User user;
    public SearchHandlerInputData(String searchQuery, User user) {
        this.searchQuery = searchQuery;
        this.user = user;
    }

    String getSearchQuery(){return searchQuery;}
    User getUser() {return user;}

}
