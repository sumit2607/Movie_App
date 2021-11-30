package com.example.movie_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.net.Credentials;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.movie_app.models.MovieModel;
import com.example.movie_app.request.Service;
import com.example.movie_app.response.MovieSearchResponse;
import com.example.movie_app.utils.MovieApi;
import com.example.movie_app.utils.credentials;
import com.example.movie_app.viewmodels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity {
  Button btn ;
  private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetRetrofitResponse();
            }
        });








    }
    private void ObserveanyChange(){
        movieListViewModel.getmMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {

            }
        });

    }







    private void GetRetrofitResponse() {
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieSearchResponse> responseCall = movieApi
                .searchMovie(
                        credentials.API_KRY,
                        "Jack Reacher ",
                        "1"
                );

        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code()==200){

                }
                List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                for (MovieModel movie: movies){
                    Log.d("tag", "the list" + movie.getRelease_date());
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });
    }
}