import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

public class API {

    // load API_TOKEN from env variable.
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    public static String getMovieTitle(String imdbID) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://www.omdbapi.com/?apikey=%s&i=%s", API_TOKEN, imdbID))
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();

            JSONObject responseBody = new JSONObject(response.body().string());
            return responseBody.getString("Title");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        String title = getMovieTitle("tt3896198");
        System.out.println(title);
    }
}
