package use_case_test.remove_rating;

import entity.Movie;
import interface_adapters.remove_rating.RemoveRatingPresenter;
import interface_adapters.remove_rating.RemoveRatingState;
import use_case.movie_info.*;
import use_case.remove_rating.*;
import interface_adapters.remove_rating.RemoveRatingViewModel;
import interface_adapters.remove_rating.RemoveRatingController;
import org.junit.Test;
import utility.ApiInterface;
import utility.OMDBCaller;
import entity.UserRating;
import data_access.UserRatingAccessObject;

import java.io.File;

import static org.junit.Assert.*;

public class RemoveRatingInteractorTest {
    //Checks if a rating will be correctly deleted from database.
    //First we set up our userRating
    //We then call our interactor and it should delete the userRating object from our database.
    //We will check if the userRating does not exist and the output Data is true (deleted).
    @Test
    public void testRemoveRatingSuccess() {
        String imdb = "tt0345950";
        String username = "Alex";
        int rating = 5;
        UserRatingAccessObject ratingAccessObject = new UserRatingAccessObject("./userRatings.csv");
        ratingAccessObject.updateRating(username, imdb, rating);
        RemoveRatingOutputBoundary presenter = new RemoveRatingOutputBoundary() {
            @Override
            public void prepareSuccessView(RemoveRatingOutputData deletedRating) {

                boolean r = deletedRating.getDeletedRating();
                assertEquals(!ratingAccessObject.userRatingExists(username, imdb), r);
            }
            @Override
            public void prepareFailView(String a) {
                fail();

            }
        };

        RemoveRatingInputData inputData = new RemoveRatingInputData(username, imdb);
        RemoveRatingInputBoundary interactor = new RemoveRatingInteractor(ratingAccessObject, presenter);
        interactor.execute(inputData); // Will send output data to presenter to be checked
    }

    //Checks if a rating is failed to be deleted.
    //If we don't initialize our userRating object by not adding it to the csv file
    //(note that we don't call the updateRating method which creates a new userRating object),
    //then the fail view should occur since the username and movieId is not found in
    //the database.
    @Test
    public void testRemoveRatingFail() {
        String imdb = "tt0345950";
        String username = "Alex";
        int rating = 5;
        UserRatingAccessObject ratingAccessObject = new UserRatingAccessObject("./userRatings.csv");
        RemoveRatingOutputBoundary presenter = new RemoveRatingOutputBoundary() {
            @Override
            public void prepareSuccessView(RemoveRatingOutputData deletedRating) {
                fail();
            }
            @Override
            public void prepareFailView(String a) {

            }
        };

        RemoveRatingInputData inputData = new RemoveRatingInputData(username, imdb);
        RemoveRatingInputBoundary interactor = new RemoveRatingInteractor(ratingAccessObject, presenter);
        interactor.execute(inputData); // Will send output data to presenter to be checked
    }

}
