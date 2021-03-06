package com.project.moviam.ui.popularmovie;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.moviam.MainActivity;
import com.project.moviam.R;
import com.project.moviam.api.ApiInterface;
import com.project.moviam.data.MovieResponse;
import com.project.moviam.retrofit.RetrofitInstance;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularMovieViewModel extends ViewModel {

    MutableLiveData<MovieResponse> moviesLiveData;
    String API_KEY="1570bcf686c0429518251321690baa6a";

    ApiInterface retrofitInstance;

    MovieResponse movieResponseList;

//    public PopularMovieViewModel() {
//        moviesLiveData = new MutableLiveData<>();
//        init(); //Call API in init method
//
//    }

    @Inject
    public PopularMovieViewModel(ApiInterface retrofitInstance){
        this.retrofitInstance=retrofitInstance;

        moviesLiveData = new MutableLiveData<>();
        init(); //Call API in init method

    }

    public void init() {
        //No need of the below statement as now we are instantiating with dagger
       //retrofitInstance = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<MovieResponse> callMovieResponseList = retrofitInstance.getPopularMovies(API_KEY);
        callMovieResponseList.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    movieResponseList = response.body();
                    moviesLiveData.setValue(movieResponseList);

                    System.out.println("Response Body: " + response.body() + "\nLive Data: " + moviesLiveData);
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        System.out.println("Error Body:" + errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("Movie", "PopularMovie OnFailure");
            }
        });

    }

}
