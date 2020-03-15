package com.project.moviam.ui.popularmovie;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.moviam.R;
import com.project.moviam.activity.MovieDescription;
import com.project.moviam.adapter.PopularMoviesAdapter;
import com.project.moviam.data.MovieResponse;
import com.project.moviam.data.ResultsItem;

public class PopularMovieFragment extends Fragment implements LifecycleOwner, PopularMoviesAdapter.PopularMovieAdapterListener, Animation.AnimationListener {

    private PopularMovieViewModel mViewModel;
    RecyclerView recyclerView;
    Animation animFadein;
    PopularMoviesAdapter popularMoviesAdapter;

    public static PopularMovieFragment newInstance() {
        return new PopularMovieFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.popular_movie_fragment, container, false);
        recyclerView = view.findViewById(R.id.movie_list_recycler);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
       // recyclerView.startAnimation(animFadein);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(PopularMovieViewModel.class);
        mViewModel.moviesLiveData.observe(this, movieListUpdateObserver);

    }

    Observer<MovieResponse> movieListUpdateObserver = new Observer<MovieResponse>() {
        @Override
        public void onChanged(MovieResponse movieResponses) {
            popularMoviesAdapter = new PopularMoviesAdapter(movieResponses, PopularMovieFragment.this::onMovieClicked);
            //recyclerView.startAnimation(animFadein);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            recyclerView.setAdapter(popularMoviesAdapter);

        }
    };

    @Override
    public void onMovieClicked(ResultsItem resultsItem) {
        if (resultsItem != null) {
            Intent intent = new Intent(getActivity(), MovieDescription.class);
            intent.putExtra("movie_title", resultsItem.getTitle());
            intent.putExtra("movie_rating", String.valueOf(resultsItem.getVoteAverage()));
            intent.putExtra("movie_release_date", resultsItem.getReleaseDate());
            intent.putExtra("movie_overview", resultsItem.getOverview());
            intent.putExtra("movie_poster", resultsItem.getPosterPath());
            intent.putExtra("movie_cover", resultsItem.getBackdropPath());
            intent.putExtra("movie_id",String.valueOf(resultsItem.getId()));
            startActivity(intent);

        } else {
            Toast.makeText(getContext(), "Empty object", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {
        Log.d("Movie", "onAnimationStart");
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Log.d("Movie", "onAnimationEnd");

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        Log.d("Movie", "onAnimationRepeat");
    }
}
