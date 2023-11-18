package interface_adapters.get_ratings;

import use_case.get_ratings.GetRatingsInputBoundary;
import use_case.get_ratings.GetRatingsInputData;

public class GetRatingsController {

    final GetRatingsInputBoundary getRatingsInputBoundary;

    public GetRatingsController(GetRatingsInputBoundary getRatingsInputBoundary) {
        this.getRatingsInputBoundary = getRatingsInputBoundary;
    }

    public void execute(String currUsername) {
        GetRatingsInputData getRatingsInputData = new GetRatingsInputData(currUsername);
        getRatingsInputBoundary.execute(getRatingsInputData);
    }
}
