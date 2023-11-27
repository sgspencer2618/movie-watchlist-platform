package interface_adapters.remove_rating;

import interface_adapters.defaultState;

public class RemoveRatingState extends defaultState {
    private String removeRatingError = null;
    private boolean removedRating;
    public RemoveRatingState(RemoveRatingState copy) {
        removeRatingError = copy.removeRatingError;
        removedRating = copy.removedRating;
    }

    public RemoveRatingState() {
    }

    public String getRemoveRatingError() {
        return removeRatingError;
    }

    public void setRemoveRatingError(String removeRatingError) {

        this.removeRatingError = removeRatingError;
    }
    public boolean getRemovedRating(){return removedRating;}

    public void setRemovedRating(boolean removedRating) {
        this.removedRating = removedRating;
    }
}
