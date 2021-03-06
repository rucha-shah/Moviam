package com.project.moviam.repository;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.project.moviam.data.DateConverter;

import java.util.Date;

@Entity(tableName = "bookmark")
public class Bookmark {

    @PrimaryKey
    private int id;

    private String title;

    private String overview;

    private String posterPath;

    private String coverPath;

    private double movieRating;

    private String movieRelease;

    public Bookmark(int id,String title, String overview, String posterPath, String coverPath, double movieRating, String movieRelease) {
        this.id=id;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.coverPath = coverPath;
        this.movieRating = movieRating;
        this.movieRelease=movieRelease;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public double getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(double movieRating) {
        this.movieRating = movieRating;
    }

    public String getMovieRelease() {
        return movieRelease;
    }

    public void setMovieRelease(String movieRelease) {
        this.movieRelease = movieRelease;
    }
}
