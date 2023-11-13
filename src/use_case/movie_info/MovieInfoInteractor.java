package use_case.movie_info;

import org.json.JSONArray;
import org.json.JSONObject;

public class MovieInfoInteractor implements MovieInfoInputBoundary {

    private MovieInfoDataAccessInterface movieInfoDataAccessObject;
    private MovieInfoOutputBoundary movieInfoPresenter;

    public MovieInfoInteractor(MovieInfoDataAccessInterface movieInfoDataAccessObject,
                               MovieInfoOutputBoundary movieInfoOutputBoundary) {
        this.movieInfoDataAccessObject = movieInfoDataAccessObject;
        this.movieInfoPresenter = movieInfoOutputBoundary;
    }

    @Override
    public void execute(MovieInfoInputData movieInfoInputData) {
        JSONObject data = movieInfoDataAccessObject.getMovie(movieInfoInputData.getImdbID());

        String title = data.getString("Title");
        String summary = data.getString("Plot");
        String rating = data.getString("Rated");

        JSONArray ratings = data.getJSONArray("Ratings");
        String imdbScore = ratings.getJSONObject(0).getString("Value");
        String rottenTomatoesScore = ratings.getJSONObject(1).getString("Value");
        String metacriticScore = ratings.getJSONObject(2).getString("Value");

        String director = data.getString("Director");
        String actors = data.getString("Actors");

        int year = data.getInt("Year");
        int runtime = data.getInt("Runtime");

        MovieInfoOutputData outputData = new MovieInfoOutputData(title, summary, rating, imdbScore,
                rottenTomatoesScore, metacriticScore, director, actors, year, runtime);

        movieInfoPresenter.prepareMovieInfoView(outputData);
    }
}
