package use_case.remove_rating;

public class RemoveRatingOutputData {
    boolean deletedRating;
     public RemoveRatingOutputData(boolean deletedRating){

         this.deletedRating = deletedRating;
     }

    public boolean getDeletedRating() {
        return deletedRating;
    }

}
