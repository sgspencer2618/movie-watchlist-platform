package interface_adapters.movie_info;

import entity.Movie;
import use_case.movie_info.MovieInfoOutputBoundary;
import use_case.movie_info.MovieInfoOutputData;


public class MovieInfoPresenter implements MovieInfoOutputBoundary {

    private final MovieInfoViewModel movieInfoViewModel;

    public MovieInfoPresenter(MovieInfoViewModel movieInfoViewModel) {
        this.movieInfoViewModel = movieInfoViewModel;
    }

    @Override
    public void prepareMovieInfoView(MovieInfoOutputData movieInfoOutputData) {
        MovieInfoState state = new MovieInfoState();

        Movie movie = movieInfoOutputData.getMovieInfo();
        state.setTitleText(movie.getTitle());

        String infoText = Integer.toString(movie.getYear());
        infoText += "  •  " + movie.getContentRating();
        infoText += "  •  " + formatRuntime(movie.getRuntime());
        infoText += "  •  " + movie.getGenres();
        state.setInfoText(infoText);

        state.setSummaryText(movie.getSummary());

        String talentText = movie.getDirector();
        talentText += "  •  " + movie.getActors();
        state.setTalentText(talentText);

        state.setImdbScore(movie.getImdbScore());
        state.setRottenTomatoesScore(movie.getRottenTomatoesScore());
        state.setMetacriticScore(movie.getMetacriticScore());

        state.setPosterURL(movie.getPosterURL());

        movieInfoViewModel.setState(state);
        movieInfoViewModel.firePropertyChanged();
    }

    private String formatRuntime(Integer runtime) {
        if(runtime == null) return "?h??m";
        int hours = runtime / 60;
        if(hours > 0) {
            return Integer.toString(hours) + "h" + Integer.toString(runtime % 60) + "m";
        }
        return Integer.toString(runtime) + "m";
    }
}
