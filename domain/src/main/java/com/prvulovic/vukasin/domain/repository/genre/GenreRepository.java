package com.prvulovic.vukasin.domain.repository.genre;

import com.prvulovic.vukasin.domain.entity.genre.Genre;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;

public interface GenreRepository {

    Single<List<Genre>> getGenre();

    Single<Map<Long,String>> getGenreIdNameMap();
}
