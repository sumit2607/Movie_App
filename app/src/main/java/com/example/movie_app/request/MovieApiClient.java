package com.example.movie_app.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movie_app.AppExecutors;
import com.example.movie_app.models.MovieModel;
import com.example.movie_app.response.MovieSearchResponse;
import com.example.movie_app.utils.credentials;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {
    public MutableLiveData<List<MovieModel>> mMovies;

    private static MovieApiClient instance;

    public static MovieApiClient getInstance(){
        if(instance==null){
            instance = new MovieApiClient();
        }
        return instance;
    }
    private  MovieApiClient(){
        mMovies = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies(String query, int pageNumber) {
        return  mMovies;
    }
    public  void searchMoviesApi(){
        final Future myHandler = AppExecutors.getInstance().getmNetworkIo().submit(new Runnable() {
            @Override
            public void run() {
                //Retrive data from api


            }
        });
        AppExecutors.getInstance().getmNetworkIo().schedule(new Runnable() {
            @Override
            public void run() {
            myHandler.cancel(true);
            }
        },400 , TimeUnit.MICROSECONDS);
    }
    private class RetrieveMovieRunnable  implements  Runnable{
        private  String query;
        private  int pageNumber;
        boolean cancelRequest;

        public RetrieveMovieRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {

            try {
                Response response= getMovies(query , pageNumber).execute();
                if (response.code()){
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());

                    mMovies.postValue(list);
                }else {
                    List<MovieModel> currentMovies = mMovies.getValue();
                    currentMovies.addAll(list);
                    mMovies.postValue(currentMovies);
                }
            }






            if (cancelRequest){
                return;
            }

            private Call<MovieSearchResponse> getMovies(String query, int pageNumber){
                return Service.getMovieApi().searchMovie(credentials.API_KRY, query,
                        pageNumber
                );
            }

        }
    }
}
