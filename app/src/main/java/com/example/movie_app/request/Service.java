package com.example.movie_app.request;

import com.example.movie_app.utils.MovieApi;
import com.example.movie_app.utils.credentials;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Service {

    private  static Retrofit.Builder builder =
            new Retrofit.Builder().baseUrl(credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());


    private static  Retrofit retrofit = builder.build();
    private  static MovieApi movieApi = retrofit.create(MovieApi.class);

    public MovieApi getMovieApi(){

        return  movieApi;
    }
}
