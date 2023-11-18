package use_case.movie_info;

import entity.MovieDetailed;
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
        String genres = data.getString("Genre");

        JSONArray ratings = data.getJSONArray("Ratings");
        String imdbScore = ratings.getJSONObject(0).getString("Value");
        String rottenTomatoesScore = ratings.getJSONObject(1).getString("Value");
        String metacriticScore = ratings.getJSONObject(2).getString("Value");

        String director = data.getString("Director");
        String actors = data.getString("Actors");

        String posterURL = data.getString("Poster");

        int year = data.getInt("Year");
        int runtime = data.getInt("Runtime");

        MovieDetailed movieInfo = new MovieDetailed(title, summary, rating, genres, imdbScore,
                rottenTomatoesScore, metacriticScore, director, actors, posterURL, year, runtime);

        MovieInfoOutputData outputData = new MovieInfoOutputData(movieInfo);
        movieInfoPresenter.prepareMovieInfoView(outputData);
    }
}
