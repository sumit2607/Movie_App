package com.example.movie_app.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movie_app.models.MovieModel;

import java.util.List;

public class MovieRepository {

    private static MovieRepository instance;
    public MutableLiveData<List<MovieModel>> mMovies = new MutableLiveData<>();
    public  static MovieRepository getInstance(){
        if (instance==null){
            instance = new MovieRepository();

        }
        return  instance;

    }
    private MovieRepository(){
        mMovies = new MutableLiveData<>();
    }
    public LiveData<List<MovieModel>> getMovies(){
        return mMovies;
    }
}
