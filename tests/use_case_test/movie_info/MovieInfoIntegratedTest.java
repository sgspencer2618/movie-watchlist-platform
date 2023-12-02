package use_case_test.movie_info;

import interface_adapters.movie_info.MovieInfoPresenter;
import interface_adapters.movie_info.MovieInfoState;
import use_case.movie_info.MovieInfoInputBoundary;
import use_case.movie_info.MovieInfoInteractor;
import use_case.movie_info.MovieInfoOutputBoundary;
import interface_adapters.movie_info.MovieInfoViewModel;
import interface_adapters.movie_info.MovieInfoController;
import org.junit.Test;
import utility.ApiInterface;
import utility.OMDBCaller;

import static org.junit.Assert.assertEquals;

public class MovieInfoIntegratedTest {

    @Test
    public void testMovieInfo() {
        MovieInfoState state = getMovieInfoState("tt0345950");

        // assertions
        assertEquals("The SpongeBob SquarePants Movie", state.getTitleText());
        assertEquals("2004  •  PG  •  1h27m  •  Animation, Adventure, Comedy", state.getInfoText());
        assertEquals("After King Neptune's crown is stolen, SpongeBob and Patrick go on a quest in " +
                "6 days to retrieve his crown. On the way SpongeBob and Patrick defeat many evildoers using " +
                "their brains and bronzes. While this is happening someone is taking over Bikini Bottom and " +
                "SpongeBob and Patrick must defeat this mastermind.", state.getSummaryText());
        assertEquals("Stephen Hillenburg, Mark Osborne  •  Tom Kenny, Jeffrey Tambor, Rodger Bumpass",
                state.getTalentText());
        assertEquals("7.1/10", state.getImdbScore());
        assertEquals("68%", state.getRottenTomatoesScore());
        assertEquals("66/100", state.getMetacriticScore());
        assertEquals("https://m.media-amazon.com/images/M/MV5BZjM5YjI0NmQtOTk4OS00NTNiLThkNzQtNT" +
                "ZlNGE4Y2VmNmU3XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg", state.getPosterURL());

    }

    private static MovieInfoState getMovieInfoState(String imdbID) {
        ApiInterface apiCaller = new OMDBCaller();

        MovieInfoViewModel movieInfoViewModel = new MovieInfoViewModel();
        MovieInfoOutputBoundary movieInfoPresenter = new MovieInfoPresenter(movieInfoViewModel);

        MovieInfoInputBoundary movieInfoInteractor = new MovieInfoInteractor(apiCaller, movieInfoPresenter);
        MovieInfoController movieInfoController = new MovieInfoController(movieInfoInteractor);

        movieInfoController.execute(imdbID);
        return movieInfoViewModel.getState();
    }
}
