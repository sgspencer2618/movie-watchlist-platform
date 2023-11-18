package use_case.get_ratings;

import entity.Movie;

import java.util.HashMap;
import java.util.List;

public class GetRatingsOutputData {
    private final boolean useCaseFailed;
    private final HashMap<Movie, Integer> ratings;

    public GetRatingsOutputData(boolean useCaseFailed, HashMap<Movie, Integer> ratings) {
        this.useCaseFailed = useCaseFailed;
        this.ratings = ratings;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public HashMap<Movie, Integer> getRatings() {return ratings;}
}
