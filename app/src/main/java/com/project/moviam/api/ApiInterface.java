package com.project.moviam.api;

import com.project.moviam.data.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String api_key);

//    @GET("/json/glide.json")
//    Call<List<PopularMoviesData>> getPopularMovies();

}
