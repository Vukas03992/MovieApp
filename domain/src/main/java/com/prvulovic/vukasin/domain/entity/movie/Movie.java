package com.prvulovic.vukasin.domain.entity.movie;

import com.prvulovic.vukasin.domain.entity.cast.CastMember;
import com.prvulovic.vukasin.domain.entity.genre.Genre;

import java.util.List;

public class Movie {

    private long id;
    private String title;
    private String releaseDate;
    private String description;
    private String posterPath;
    private String originalLanguage;
    private String originalTitle;
    private long voteCount;
    private boolean video;
    private float popularity;
    private float voteAverage;
    private List<Genre> genreIdList;
    private String backdropPath;
    private boolean adult;
    private List<CastMember> cast;
    private boolean favourite;

    public Movie(long id, String title, String releaseDate, String description, String posterPath, String originalLanguage, String originalTitle, long voteCount, boolean video, float popularity, float voteAverage, List<Genre> genreIdList, String backdropPath, boolean adult, List<CastMember> cast, boolean favourite) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.description = description;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.voteCount = voteCount;
        this.video = video;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
        this.genreIdList = genreIdList;
        this.backdropPath = backdropPath;
        this.adult = adult;
        this.cast = cast;
        this.favourite = favourite;
    }

    public Movie() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public List<Genre> getGenreList() {
        return genreIdList;
    }

    public void setGenreList(List<Genre> genreIdList) {
        this.genreIdList = genreIdList;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public List<CastMember> getCast() {
        return cast;
    }

    public void setCast(List<CastMember> cast) {
        this.cast = cast;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
