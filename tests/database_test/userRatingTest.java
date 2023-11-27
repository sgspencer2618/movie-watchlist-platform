package database_test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import data_access.UserRatingAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import entity.UserRating;

public class userRatingTest {
    UserRatingAccessObject userRatAccess = new UserRatingAccessObject("./testUserRating.csv");
    UserFactory userFac = new CommonUserFactory();
    int user_count = 0;

    @Test
    public void UserRatingTest() {
        User user = userFac.create(String.format("test%d", user_count), "badPass");
        userRatAccess.updateRating(user.getUsername(), "testMovie", 5);
        user_count++;
        assert userRatAccess.userRatingExists(user.getUsername(), "testMovie");

        // now one user rating exists
        userRatAccess.updateRating(user.getUsername(), "NewMovie", 1);
        assert userRatAccess.userRatingExists(user.getUsername(), "NewMovie");

        assert (userRatAccess.getUserRating(user.getUsername(), "testMovie").getRating() == 5);
        assert (userRatAccess.getUserRating(user.getUsername(), "NewMovie").getRating() == 1);

        userRatAccess.updateRating(user.getUsername(), "NewMovie", 3);
        assert (userRatAccess.getUserRating(user.getUsername(), "NewMovie").getRating() == 3);
        removeWatchListTest();
    }

    public void removeWatchListTest() {
        ArrayList<String> users = new ArrayList<>();
        for (int i = 0; i < user_count; i++) {
            users.add(String.format("test%d", i));
        }
        for (String username : users) {
            List<UserRating> ratings = userRatAccess.getRatings(username);
            for (UserRating rat : ratings) {
                userRatAccess.removeRating(username, rat.getMovieId());
                assert !(userRatAccess.userRatingExists(username, rat.getMovieId()));
            }
        }
    }
}