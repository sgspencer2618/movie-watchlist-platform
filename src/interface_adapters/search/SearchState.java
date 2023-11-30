package interface_adapters.search;
import interface_adapters.defaultState;

public class SearchState extends defaultState{
    // Search state needs nothing more than the default to work.
    private String searchQuery = "";

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String query) {
        this.searchQuery = query;
    }
}
