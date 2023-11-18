package use_case.get_ratings;

public class GetRatingsInputData {
    private final String currUsername;

    public GetRatingsInputData(String currUsername) {
        this.currUsername = currUsername;
    }

    public String getCurrUsername() {
        return currUsername;
    }
}
