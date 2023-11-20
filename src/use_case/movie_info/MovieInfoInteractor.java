package use_case.movie_info;

import entity.Movie;
import utility.ApiInterface;

public class MovieInfoInteractor implements MovieInfoInputBoundary {

    private final ApiInterface apiInterface;
    private final MovieInfoOutputBoundary movieInfoPresenter;

    public MovieInfoInteractor(ApiInterface apiInterface,
                               MovieInfoOutputBoundary movieInfoOutputBoundary) {
        this.apiInterface = apiInterface;
        this.movieInfoPresenter = movieInfoOutputBoundary;
    }

    @Override
    public void execute(MovieInfoInputData movieInfoInputData) {
        Movie movieDetailed = apiInterface.getMovie(movieInfoInputData.getImdbID());
        MovieInfoOutputData outputData = new MovieInfoOutputData(movieDetailed);
        movieInfoPresenter.prepareMovieInfoView(outputData);
    }
}
