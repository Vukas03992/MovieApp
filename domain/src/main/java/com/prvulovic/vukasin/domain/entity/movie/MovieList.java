package com.prvulovic.vukasin.domain.entity.movie;

import java.util.List;

public class MovieList {

    private int page;
    private int movieCount;
    private int pageCount;

    private List<Movie> movieList;

    public MovieList(int page, int movieCount, int pageCount, List<Movie> movieList) {
        this.page = page;
        this.movieCount = movieCount;
        this.pageCount = pageCount;
        this.movieList = movieList;
    }

    public MovieList() {
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

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
