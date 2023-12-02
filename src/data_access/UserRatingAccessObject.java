package data_access;
import entity.UserRating;
import use_case.get_ratings.GetRatingsDataAccessInterface;
import use_case.remove_rating.RemoveRatingDataAccessInterface;
import use_case.update_rating.UpdateRatingDataAccessInterface;


import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

import java.util.List;
import java.util.Map;

public class UserRatingAccessObject implements GetRatingsDataAccessInterface,
        RemoveRatingDataAccessInterface, UpdateRatingDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final ArrayList<UserRating> ratings = new ArrayList<>();

    public UserRatingAccessObject(String csvPath) {
        csvFile = new File(csvPath);
        headers.put("movieID", 0);
        headers.put("username", 1);
        headers.put("rating", 2);

        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                assert header.equals("movieID,username,rating");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String movieID = String.valueOf(col[headers.get("movieID")]);
                    String username = String.valueOf(col[headers.get("username")]);
                    int ratingValue = Integer.parseInt(String.valueOf(col[headers.get("rating")]));
                    UserRating rating = new UserRating(movieID, username, ratingValue);
                    ratings.add(rating);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void removeRating(String username, String movieID){
        //Iterator as we are changing the arraylist
        ratings.removeIf(rat -> rat.getMovieId().equals(movieID) && rat.getUsername().equals(username));
        this.save();
    }
    public void updateRating(String username, String movieID, int newRating){
        boolean found = false;
        for (UserRating rat : ratings) {
            if ((rat.getMovieId().equals(movieID)) && (rat.getUsername().equals(username))) {
                rat.setRating(newRating);
                found = true;
            }
        }
        // If we get here, no rating currently exists, so make one.
        if (!found) {
            UserRating newRat = new UserRating(movieID, username, newRating);
            ratings.add(newRat);
        }
        this.save();

    }

    public boolean userRatingExists(String user, String movieID){
        for (UserRating rat : ratings) {
            if ((rat.getMovieId().equals(movieID)) && (rat.getUsername().equals(user))) {
                return true;
            }
        }
        return false;
    }

    public UserRating getUserRating(String user, String movieID){
       for (UserRating rat : ratings) {
            if ((rat.getMovieId().equals(movieID)) && (rat.getUsername().equals(user))) {
                return rat;
            }
        }
        throw new RuntimeException("UserRating does not exist");
    }

    public List<UserRating> getRatings(String user){
        List<UserRating> userRats = new ArrayList<>();
        for (UserRating rat : ratings) {
            String username = rat.getUsername();
            if (username.equals(user)) {
                userRats.add(rat);
            }
        }
        return userRats;
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (UserRating rating : ratings) {
                String line = String.format("%s,%s,%d",
                        rating.getMovieId(), rating.getUsername(), rating.getRating());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
