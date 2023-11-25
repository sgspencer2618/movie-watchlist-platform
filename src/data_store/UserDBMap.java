package data_store;

import entity.Movie;
import entity.User;
import entity.UserFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class UserDBMap implements UserDBInterface {

    private final HashMap<String, UserStore> users;
    private MovieDBInterface movieDB;

    private UserFactory userFactory;

    public UserDBMap(UserFactory userFactory) {
        this.userFactory = userFactory;
        this.users = new HashMap<>();
    }

    @Override
    public User getUser(String username) {
        UserStore user = users.get(username);
        if (user == null) {
            return null;
        }
        return userFactory.create(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    @Override
    public boolean addUser(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, new UserStore(username, password));
        return true;
    }



    @Override
    public List<Movie> getWatchlist(String username) {
        Collection<UserMovie> movieList = users.get(username).getUserMovies().values();
        List<Movie> returnList = new ArrayList<>();
        for (UserMovie userMovie : movieList) {
            if (!userMovie.isInWatchlist()){
                continue;
            }
            MovieStore movieStore = movieDB.getMovie(userMovie.getMovieID());
            Movie movie = new Movie(movieStore.getMovieID(), movieStore.getTitle(), movieStore.getPosterPath(), movieStore.getYear());
            movie.setInWatchlist(userMovie.isInWatchlist());
            movie.setUserRating(userMovie.getRating());
            returnList.add(movie);
        }
        return returnList;
    }

    @Override
    public void addToWatchlist(String username, String movieID) {
        HashMap<String, UserMovie> userMovies = users.get(username).getUserMovies();
        if (userMovies.containsKey(movieID)) {
            userMovies.get(movieID).setInWatchlist(true);
        } else {
            UserMovie movie = new UserMovie(movieID);
            movie.setInWatchlist(true);
            userMovies.put(movie.getMovieID(), movie);
        }
    }

    @Override
    public void removeFromWatchlist(String username, String movieID) {
        HashMap<String, UserMovie> userMovies = users.get(username).getUserMovies();
        if (userMovies.containsKey(movieID)) {
            UserMovie userMovie = userMovies.get(movieID);
            if(userMovie.getRating() == 0){
                userMovies.remove(movieID);
            } else {
                userMovie.setInWatchlist(false);
            }
        }
    }

    @Override
    public List<Movie> getRatings(String username) {
        Collection<UserMovie> movieList = users.get(username).getUserMovies().values();
        List<Movie> returnList = new ArrayList<>();
        for (UserMovie userMovie : movieList) {
            if (userMovie.getRating() == 0){
                continue;
            }
            MovieStore movieStore = movieDB.getMovie(userMovie.getMovieID());
            Movie movie = new Movie(movieStore.getMovieID(), movieStore.getTitle(), movieStore.getPosterPath(), movieStore.getYear());
            movie.setInWatchlist(userMovie.isInWatchlist());
            movie.setUserRating(userMovie.getRating());
            returnList.add(movie);
        }
        return returnList;
    }

    @Override
    public void addRating(String username, String movieID, int rating) {
        HashMap<String, UserMovie> userMovies = users.get(username).getUserMovies();
        if (userMovies.containsKey(movieID)) {
            userMovies.get(movieID).setRating(rating);
        } else {
            UserMovie movie = new UserMovie(movieID);
            movie.setRating(rating);
            userMovies.put(movie.getMovieID(), movie);
        }
    }

    @Override
    public void removeRating(String username, String movieID) {
        HashMap<String, UserMovie> userMovies = users.get(username).getUserMovies();
        if (userMovies.containsKey(movieID)) {
            UserMovie userMovie = userMovies.get(movieID);
            if(!userMovie.isInWatchlist()){
                userMovies.remove(movieID);
            } else {
                userMovie.setRating(0);
            }
        }
    }
}
