package com.example.movie_app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movie_app.models.MovieModel;
import com.example.movie_app.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {


    private MovieRepository movieRepository;


    public MovieListViewModel() {
        movieRepository =  MovieRepository.getInstance();
    }

//    public LiveData<List<MovieModel>> getMovies(){
//
//    }

    public LiveData<List<MovieModel>> getmMovies() {
        return movieRepository.getMovies();
    }
}
