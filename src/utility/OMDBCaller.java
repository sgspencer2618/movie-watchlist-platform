package utility;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.HttpUrl;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Movie entity to return
import entity.Movie;

@SuppressWarnings("KotlinInternalInJava")
public class OMDBCaller implements ApiInterface {
    private String API_TOKEN;
    private final OkHttpClient client;
    private final String url;

    public OMDBCaller() {
        this.url = "https://www.omdbapi.com/";
        this.client = new OkHttpClient().newBuilder().build();
        this.set_API_TOKEN();
    }
    
    private void set_API_TOKEN () {
        try {
            String userDir = System.getProperty("user.dir");
            File myFile = new File(userDir + "/.secret.txt");
            Scanner reader = new Scanner(myFile);
            this.API_TOKEN = reader.nextLine();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public List<Movie> getSearch(String search, int page) {
        HttpUrl.Builder httpUrl = HttpUrl.parse(this.url).newBuilder()
                .addQueryParameter("apiKey", this.API_TOKEN)
                .addQueryParameter("s", search)
                .addQueryParameter("p", Integer.toString(page))
                .addQueryParameter("type", "movie");
        Request request = new Request.Builder()
                .url(httpUrl.build())
                .addHeader("Content-Type", "application/json")
                .build();
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            assert response.code() == 200;
            JSONObject response_json = new JSONObject(response.body().string());
            assert response_json.has("Search");
            JSONArray jsonMovies = response_json.getJSONArray("Search");
            for (int i = 0; i < jsonMovies.length(); i++) {
                JSONObject json_movie = jsonMovies.getJSONObject(i);
                Movie new_movie = new Movie(
                        json_movie.getString("imdbID"),
                        json_movie.getString("Title"),
                        json_movie.getString("Poster"),
                        json_movie.getInt("Year")
                );
                movies.add(new_movie);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       return movies;
    }
    
    public Movie getMovie(String imdbID) {
        HttpUrl.Builder httpUrl = HttpUrl.parse(this.url).newBuilder()
                .addQueryParameter("apiKey", this.API_TOKEN)
                .addQueryParameter("i", imdbID)
                .addQueryParameter("plot", "full")
                .addQueryParameter("type", "movie");
        Request request = new Request.Builder()
                .url(httpUrl.build())
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            assert response.code() == 200;
            JSONObject response_json = new JSONObject(response.body().string());
            assert response_json.has("Title");
            ArrayList<String> ratings = new ArrayList<>();
            JSONArray ratings_json = response_json.getJSONArray("Ratings");
            // RATINGS COME IN ORDER:
            // imdbRating
            // rottenTomatoes
            // Metacritic
            String imdbRating = "N/A";
            String rottenTomatoesRating = "N/A";
            String metacriticRating = "N/A";

            if(ratings_json.length() >= 1) {
                imdbRating = ratings_json.getJSONObject(0).getString("Value");
            }
            if(ratings_json.length() >= 2) {
                rottenTomatoesRating = ratings_json.getJSONObject(1).getString("Value");
            }
            if(ratings_json.length() >= 3) {
                metacriticRating = ratings_json.getJSONObject(2).getString("Value");
            }

            // Find runtime in minutes as int with regex
            Integer runtime = null;
            String rt = response_json.getString("Runtime");
            if(!rt.equals("N/A")) {
                Matcher matcher = Pattern.compile("\\d+").matcher(rt);
                matcher.find();
                runtime = Integer.parseInt(matcher.group());
            }

            return new Movie(
                    response_json.getString("imdbID"),
                    response_json.getString("Title"),
                    response_json.getString("Plot"),
                    response_json.getString("Rated"),
                    response_json.getString("Genre"),
                    imdbRating,
                    rottenTomatoesRating,
                    metacriticRating,
                    response_json.getString("Director"),
                    response_json.getString("Actors"),
                    response_json.getString("Poster"),
                    response_json.getInt("Year"),
                    runtime
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
