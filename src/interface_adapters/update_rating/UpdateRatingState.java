package interface_adapters.update_rating;

import interface_adapters.defaultState;

public class UpdateRatingState extends defaultState {
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
