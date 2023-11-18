package use_case.movie_info;

import entity.Movie;

public class MovieInfoInteractor implements MovieInfoInputBoundary {

    private final MovieInfoDataAccessInterface movieInfoDataAccessObject;
    private final MovieInfoOutputBoundary movieInfoPresenter;

    public MovieInfoInteractor(MovieInfoDataAccessInterface movieInfoDataAccessObject,
                               MovieInfoOutputBoundary movieInfoOutputBoundary) {
        this.movieInfoDataAccessObject = movieInfoDataAccessObject;
        this.movieInfoPresenter = movieInfoOutputBoundary;
    }

    @Override
    public void execute(MovieInfoInputData movieInfoInputData) {
        Movie movieDetailed = movieInfoDataAccessObject.getMovie(movieInfoInputData.getImdbID());
        MovieInfoOutputData outputData = new MovieInfoOutputData(movieDetailed);
        movieInfoPresenter.prepareMovieInfoView(outputData);
    }
}
