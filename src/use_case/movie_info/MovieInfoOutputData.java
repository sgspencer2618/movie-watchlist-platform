package use_case.movie_info;

import entity.Movie;

public class MovieInfoOutputData {

    private Movie movieInfo;

    public MovieInfoOutputData(Movie movieInfo) {
        this.movieInfo = movieInfo;
    }

    public Movie getMovieInfo() {
        return movieInfo;
    }
}
