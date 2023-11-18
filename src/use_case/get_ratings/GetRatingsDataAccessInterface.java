package use_case.get_ratings;

import entity.Movie;

import java.util.List;

public interface GetRatingsDataAccessInterface {
    public List<Movie> getWatchlist(String user);
}
