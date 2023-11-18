package use_case.movie_info;

import entity.MovieDetailed;

public class MovieInfoOutputData {

    private MovieDetailed movieInfo;

    public MovieInfoOutputData(MovieDetailed movieInfo) {
        this.movieInfo = movieInfo;
    }

    public MovieDetailed getMovieInfo() {
        return movieInfo;
    }
}
