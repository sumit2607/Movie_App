package com.example.movie_app.response;

import com.example.movie_app.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {
    @SerializedName("results")
    @Expose
    private MovieModel movie;

    private  MovieModel getMovie(){
       return movie;
    }

    //
}
