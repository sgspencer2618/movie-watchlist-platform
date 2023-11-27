package data_access;
import entity.UserRating;
import use_case.get_ratings.GetRatingsDataAccessInterface;
import use_case.remove_rating.RemoveRatingDataAccessInterface;
import use_case.search.SearchHandlerDataAccessInterface;
import use_case.update_rating.UpdateRatingDataAccessInterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserRatingAccessObject implements GetRatingsDataAccessInterface,
        RemoveRatingDataAccessInterface, UpdateRatingDataAccessInterface, SearchHandlerDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<Integer, UserRating> ratings = new HashMap<>();

    public UserRatingAccessObject(String csvPath) {
        csvFile = new File(csvPath);
        headers.put("id", 0);
        headers.put("movieID", 1);
        headers.put("username", 2);
        headers.put("rating", 3);

        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                assert header.equals("id,movieID,username,rating");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    Integer id = Integer.parseInt(String.valueOf(col[headers.get("id")]));
                    String movieID = String.valueOf(col[headers.get("movieID")]);
                    String username = String.valueOf(col[headers.get("username")]);
                    Integer ratingValue = Integer.parseInt(String.valueOf(col[headers.get("rating")]));
                    UserRating rating = new UserRating(id, movieID, username, ratingValue);
                    ratings.put(id, rating);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void removeRating(String username, String movieID){
        Collection<UserRating> loopRat = ratings.values();
        for (UserRating rat : loopRat) {
            if ((rat.getMovieId() == movieID) && (rat.getUsername() == username)) {
                ratings.remove(rat.getId());
            }
        }
    }
    public void updateRating(String username, String movieID, int newRating){
        Collection<UserRating> loopRat = ratings.values();
        for (UserRating rat : loopRat) {
            if ((rat.getMovieId() == movieID) && (rat.getUsername() == username)) {
                rat.setRating(newRating);
            }
        }
    }

    public boolean userRatingExists(String user, String movieID){
        for (UserRating rat : ratings.values()) {
            if ((rat.getMovieId() == movieID) && (rat.getUsername() == user)) {
                return true;
            }
        }
        return false;
    }

    public UserRating getUserRating(String user, String movieID){
       for (UserRating rat : ratings.values()) {
            if ((rat.getMovieId() == movieID) && (rat.getUsername() == user)) {
                return rat;
            }
        }
        throw new RuntimeException("UserRating does not exist");
    }

    public List<UserRating> getRatings(String user){
        List<UserRating> userRats = new ArrayList<>();
        for (UserRating rat : ratings.values()) {
            if (rat.getUsername() == user) {
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

            for (UserRating rating : ratings.values()) {
                String line = String.format("%d,%d,%s,%d",
                        rating.getId(), rating.getMovieId(), rating.getUsername(), rating.getRating());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
