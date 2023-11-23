package app;

import interface_adapters.movie_info.MovieInfoController;
import interface_adapters.movie_info.MovieInfoPresenter;
import interface_adapters.movie_info.MovieInfoViewModel;
import use_case.movie_info.MovieInfoInputBoundary;
import use_case.movie_info.MovieInfoInteractor;
import use_case.movie_info.MovieInfoOutputBoundary;
import utility.ApiInterface;
import view.MovieInfoView;

public class MovieInfoUseCaseFactory {

    /** Prevent instantiation. */
    private MovieInfoUseCaseFactory() {}

    public static MovieInfoView create(ApiInterface api, MovieInfoViewModel viewmodel) {
        MovieInfoController controller = createMovieInfoUseCase(api, viewmodel);
        return new MovieInfoView(controller, viewmodel);
    }

    public static MovieInfoController createMovieInfoUseCase(ApiInterface api, MovieInfoViewModel viewmodel) {
        MovieInfoOutputBoundary movieInfoPresenter = new MovieInfoPresenter(viewmodel);
        MovieInfoInputBoundary movieInfoInteractor = new MovieInfoInteractor(api, movieInfoPresenter);
        return new MovieInfoController(movieInfoInteractor);
    }
}
