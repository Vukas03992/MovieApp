package com.prvulovic.vukasin.domain.repository.cast;

import com.prvulovic.vukasin.domain.entity.cast.CastMember;

import java.util.List;

import io.reactivex.Single;

public interface CastRepository {

    Single<List<CastMember>> getCast(long movieId);
}
