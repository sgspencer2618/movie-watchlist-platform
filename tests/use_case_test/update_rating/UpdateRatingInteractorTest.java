package use_case_test.update_rating;

import entity.Movie;
import interface_adapters.update_rating.UpdateRatingPresenter;
import interface_adapters.update_rating.UpdateRatingState;
import use_case.movie_info.*;
import use_case.remove_rating.*;
import use_case.update_rating.*;
import interface_adapters.update_rating.UpdateRatingViewModel;
import interface_adapters.update_rating.UpdateRatingController;
import org.junit.Test;
import utility.ApiInterface;
import utility.OMDBCaller;
import entity.UserRating;
import data_access.UserRatingAccessObject;

import java.io.File;

import static org.junit.Assert.*;

public class UpdateRatingInteractorTest {
    @Test
    public void testUpadateRating() {
        String imdb = "tt0345950";
        String username = "Alex";
        int r = 5;
        UserRatingAccessObject ratingAccessObject = new UserRatingAccessObject("./userRatings.csv");
        ratingAccessObject.updateRating(username, imdb, r);
        UpdateRatingOutputBoundary presenter = new UpdateRatingOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateRatingOutputData rating) {

                int x = ratingAccessObject.getUserRating(username, imdb).getRating();
                assertEquals(x, rating.getNewRating());
            }
        };

        UpdateRatingInputData inputData = new UpdateRatingInputData(3, username, imdb);
        UpdateRatingInputBoundary interactor = new UpdateRatingInteractor(ratingAccessObject, presenter);
        interactor.execute(inputData); // Will send output data to successPresenter to be checked
    }

}
