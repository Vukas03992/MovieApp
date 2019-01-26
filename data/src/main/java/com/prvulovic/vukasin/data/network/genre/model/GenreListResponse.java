package com.prvulovic.vukasin.data.network.genre.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenreListResponse {

    @SerializedName("genres")
    private List<GenreResponse> genreResponseList;

    public GenreListResponse() {
    }

    public List<GenreResponse> getGenreResponseList() {
        return genreResponseList;
    }

    public void setGenreResponseList(List<GenreResponse> genreResponseList) {
        this.genreResponseList = genreResponseList;
    }
}
