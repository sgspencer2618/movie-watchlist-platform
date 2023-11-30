package data_store;
import java.util.HashMap;

// Class which accesses the movie hashmap
public class MovieDBMap implements MovieDBInterface{

    private HashMap<String, MovieStore> movies;

    public MovieDBMap(){
        this.movies = new HashMap<>();
    }

    public HashMap<String, MovieStore> getMovies(){return movies;}

    public void setMovies(HashMap<String, MovieStore> movies){this.movies = movies;}

    public void addMovie(MovieStore movie){
        movies.put(movie.getMovieID(), movie);
    }

    public void removeMovie(String movieID){
        movies.remove(movieID);
    }

    public MovieStore getMovie(String movieID){
        return movies.get(movieID);
    }
}
