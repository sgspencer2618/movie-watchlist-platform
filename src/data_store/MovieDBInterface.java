package data_store;

public interface MovieDBInterface {

    public void addMovie(MovieStore movie);

    public void removeMovie(String movieID);

    public MovieStore getMovie(String movieID);
}
