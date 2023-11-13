package use_case.search;

public class SearchHandlerInputData {

    final private String searchQuery;
    public SearchHandlerInputData(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    String getSearchQuery(){return searchQuery;}

}
