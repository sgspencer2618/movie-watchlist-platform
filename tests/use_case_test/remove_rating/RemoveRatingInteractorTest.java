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

    @Test
    public void testRemoveRatingSuccess() {
        String imdb = "tt0345950";
        String username = "Alex";
        int rating = 5;
        UserRatingAccessObject ratingAccessObject = new UserRatingAccessObject("./userRatings.csv");
        ratingAccessObject.updateRating(imdb, username, rating);
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
        interactor.execute(inputData); // Will send output data to successPresenter to be checked
    }

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
        interactor.execute(inputData); // Will send output data to successPresenter to be checked
    }

}
