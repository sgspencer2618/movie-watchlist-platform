package interface_adapters.movie_info;

import use_case.movie_info.MovieInfoInputBoundary;
import use_case.movie_info.MovieInfoInputData;

public class MovieInfoController {

    final MovieInfoInputBoundary movieInfoInteractor;

    public MovieInfoController(MovieInfoInputBoundary movieInfoInteractor) {
        this.movieInfoInteractor = movieInfoInteractor;
    }

    public void execute(String imdbID) {
        MovieInfoInputData inputData = new MovieInfoInputData(imdbID);
        movieInfoInteractor.execute(inputData);
    }
}
