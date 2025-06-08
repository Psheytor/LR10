package json.demolr8.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import json.demolr8.model.Movie;
import json.demolr8.repo.MovieRepo;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MovieService {

    private static final String FILE_PATH = System.getProperty("user.home") + File.separator + "movies.json";
    private static MovieService instance;
    private final MovieRepo repo;

    private MovieService() {
        this.repo = new MovieRepo();
    }

    public static synchronized MovieService getInstance() {
        if (instance == null) {
            instance = new MovieService();
        }
        return instance;
    }

    public synchronized void saveMovie(Movie movie) throws IOException {
        repo.save(movie);
    }

    public synchronized List<Movie> getAllMovies() throws IOException {
        return repo.findAll();
    }
}
