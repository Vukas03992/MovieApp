package com.prvulovic.vukasin.data.network.genre.model;

import com.google.gson.annotations.SerializedName;

public class GenreResponse {

    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;

    public GenreResponse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
