package com.project.moviam.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.moviam.R;
import com.project.moviam.data.MovieResponse;
import com.project.moviam.data.ResultsItem;
import com.project.moviam.repository.Bookmark;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder> {

    List<Bookmark> bookmarkList;
    String baseImageUrl = "https://image.tmdb.org/t/p/w185";
    BookmarkAdapterListener bookmarkAdapterListener;

    public static class BookmarkViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImage;

        public BookmarkViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movie_poster_image);
        }
    }

    public BookmarkAdapter(List<Bookmark> bookmarkList,BookmarkAdapterListener bookmarkAdapterListener) {
        this.bookmarkList = bookmarkList;
        this.bookmarkAdapterListener = bookmarkAdapterListener;
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_recycler_items, parent, false);
        BookmarkViewHolder bookmarkViewHolder = new BookmarkViewHolder(view);
        return bookmarkViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {

        Picasso.with(holder.movieImage.getContext()).load(baseImageUrl + bookmarkList.get(position).getPosterPath()).resize(650, 1050).into(holder.movieImage);
        holder.movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookmarkAdapterListener.onMovieClicked(bookmarkList.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        if (bookmarkList != null)
            return bookmarkList.size();
        else
            return 0;
    }

    public interface BookmarkAdapterListener {
        void onMovieClicked(Bookmark resultsItem);
    }
}
