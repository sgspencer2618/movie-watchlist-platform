package data_access;
import entity.UserRating;
import use_case.get_ratings.GetRatingsDataAccessInterface;
import use_case.remove_rating.RemoveRatingDataAccessInterface;
import use_case.search.SearchHandlerDataAccessInterface;
import use_case.update_rating.UpdateRatingDataAccessInterface;

import utility.ApiInterface;
import utility.OMDBCaller;
import view.RatingsView;

import javax.print.attribute.HashAttributeSet;
import java.util.ArrayList;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

import java.util.List;
import java.util.Map;

public class UserRatingAccessObject implements GetRatingsDataAccessInterface,
        RemoveRatingDataAccessInterface, UpdateRatingDataAccessInterface, SearchHandlerDataAccessInterface {

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
                    Integer ratingValue = Integer.parseInt(String.valueOf(col[headers.get("rating")]));
                    UserRating rating = new UserRating(movieID, username, ratingValue);
                    ratings.add(rating);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
          
    public void removeRating(String username, String move_id){}
    public void updateRating(String username, String move_id, int newRating){}
    public UserRating getUserRating(String user, String movieID){
        // TODO: Fix when database is done. All these methods should return a UserRating object, not some arbitrary integer.
        return new UserRating(0, "", user, 0);
      
    public void removeRating(String username, String movieID){
        Iterator<UserRating> itr = ratings.iterator();
        while(itr.hasNext()) { //Iterator as we are changing the arraylist
            UserRating rat = itr.next();
            if (rat.getMovieId().equals(movieID) && rat.getUsername().equals(username)) {
                itr.remove();
            }
        }
        this.save();
    }
    public void updateRating(String username, String movieID, int newRating){
        ArrayList<UserRating> loopRat = ratings;
        boolean found = false;
        for (UserRating rat : loopRat) {
            if ((rat.getMovieId() == movieID) && (rat.getUsername() == username)) {
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
            if ((rat.getMovieId() == movieID) && (rat.getUsername() == user)) {
                return true;
            }
        }
        return false;
    }

    public UserRating getUserRating(String user, String movieID){
       for (UserRating rat : ratings) {
            if ((rat.getMovieId() == movieID) && (rat.getUsername() == user)) {
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


    public List<UserRating> getRatings(String user){
        ApiInterface APIcaller = new OMDBCaller();
        List<UserRating> ratings = new ArrayList<UserRating>();
        Movie movie1 = APIcaller.getMovie("tt1229238");
        Movie movie2 = APIcaller.getMovie("tt2911666");
        UserRating rating1 = new UserRating(1,movie1.getImdbID(), user, 5);
        UserRating rating2 = new UserRating(2,movie2.getImdbID(), user, 3);
        ratings.add(rating1);
        ratings.add(rating2);
//        ratings.put(APIcaller.getMovie("tt1375666"), 5); // Inception
//        ratings.put(APIcaller.getMovie("tt10648342"), 2); // Thor: Love and Thunder
//        ratings.put(APIcaller.getMovie("tt2935510"), 3); // Ad Astra
        return ratings;

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
