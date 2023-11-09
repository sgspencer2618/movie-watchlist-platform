package use_case.search;

public interface SearchHandlerOutputBoundary {

    void prepareSuccessView(SearchHandlerOutputData result);
    void prepareFailView(String error);
}
