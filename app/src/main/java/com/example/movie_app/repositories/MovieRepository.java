package com.example.movie_app.repositories;

import androidx.lifecycle.LiveData;

import com.example.movie_app.models.MovieModel;
import com.example.movie_app.request.MovieApiClient;

import java.util.List;

public class MovieRepository {

    private static MovieRepository instance;
    private MovieApiClient movieApiClient;
    public  static MovieRepository getInstance(){
        if (instance==null){
            instance = new MovieRepository();

        }
        return  instance;

    }
    private MovieRepository(){
       movieApiClient = MovieApiClient.getInstance();
    }
    public LiveData<List<MovieModel>> getMovies(){
        return movieApiClient.getMovies(query, pageNumber);
    }
}
