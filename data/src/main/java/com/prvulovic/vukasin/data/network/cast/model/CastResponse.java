package com.prvulovic.vukasin.data.network.cast.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastResponse {

    @SerializedName("id")
    private long id;
    @SerializedName("cast")
    private List<CastMemberResponse> castMemberResponses;

    public CastResponse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<CastMemberResponse> getCastMemberResponses() {
        return castMemberResponses;
    }

    public void setCastMemberResponses(List<CastMemberResponse> castMemberResponses) {
        this.castMemberResponses = castMemberResponses;
    }
}
