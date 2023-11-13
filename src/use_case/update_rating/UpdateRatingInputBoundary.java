package use_case.update_rating;

import use_case.remove_rating.RemoveRatingInputData;

public interface UpdateRatingInputBoundary {
    void execute(UpdateRatingInputData updateRatingInputData);
}
