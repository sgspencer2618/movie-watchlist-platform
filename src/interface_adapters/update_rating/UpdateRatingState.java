package interface_adapters.update_rating;

import interface_adapters.remove_rating.RemoveRatingState;

public class UpdateRatingState {
    private int newRating;
    public UpdateRatingState(UpdateRatingState copy) {
        newRating = copy.newRating;
    }
    public UpdateRatingState() {
    }

    public int getNewRating() {
            return newRating;
    }

        public void setNewRating(int newRating) {
            this.newRating = newRating;
        }

}

