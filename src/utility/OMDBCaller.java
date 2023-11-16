package utility;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.HttpUrl;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class OMDBCaller implements ApiInterface{
    private String API_TOKEN;
    private final OkHttpClient client;
    private final String url;

    private void set_API_TOKEN () {
        try {
            String userDir = System.getProperty("user.dir");
            System.out.println(userDir);
            File myFile = new File(userDir + "/.secret.txt");
            Scanner reader = new Scanner(myFile);
            this.API_TOKEN = reader.nextLine();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public OMDBCaller() {
        this.url = "https://www.omdbapi.com/";
        this.client = new OkHttpClient().newBuilder().build();
        this.set_API_TOKEN();
    }
    public JSONObject getSearch(String search, int page) {
        HttpUrl.Builder httpUrl = HttpUrl.parse(this.url).newBuilder()
                .addQueryParameter("apiKey", this.API_TOKEN)
                .addQueryParameter("s", search)
                .addQueryParameter("p", Integer.toString(page))
                .addQueryParameter("type", "movie");
        Request request = new Request.Builder()
                .url(httpUrl.build())
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            return new JSONObject(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public JSONObject getMovie(String imdbID) {
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
            assert response.code() == 200;
            assert response.body() != null;
            return new JSONObject(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
