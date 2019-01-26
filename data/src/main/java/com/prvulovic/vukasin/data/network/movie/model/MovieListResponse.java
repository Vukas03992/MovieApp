package com.prvulovic.vukasin.data.network.movie.model;

import com.google.gson.annotations.SerializedName;
import com.prvulovic.vukasin.domain.entity.movie.Movie;

import java.util.List;

public class MovieListResponse {

    @SerializedName("page")
    private int page;
    @SerializedName("total_results")
    private int movieCount;
    @SerializedName("total_pages")
    private int pageCount;

    @SerializedName("results")
    private List<MovieResponse> movieList;

    public MovieListResponse() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getMovieCount() {
        return movieCount;
    }

    public void setMovieCount(int movieCount) {
        this.movieCount = movieCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<MovieResponse> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieResponse> movieList) {
        this.movieList = movieList;
    }
}
