package use_case.movie_info;

public class MovieInfoInputData {

    private String imdbID;

    public MovieInfoInputData(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getImdbID() {
        return imdbID;
    }
}
