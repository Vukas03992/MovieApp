package com.prvulovic.vukasin.data.network.genre.mapper;

import com.prvulovic.vukasin.data.network.genre.model.GenreListResponse;
import com.prvulovic.vukasin.data.network.genre.model.GenreResponse;
import com.prvulovic.vukasin.domain.entity.genre.Genre;

import java.util.ArrayList;
import java.util.List;

public class GenreMapper {

    public static List<Genre> genreListResponseToGenreList(GenreListResponse response){
        List<Genre> genreList=new ArrayList<>();
        if (response.getGenreResponseList()!=null && !response.getGenreResponseList().isEmpty()){
            for (GenreResponse genreResponse : response.getGenreResponseList()) {
                genreList.add(genreResponseToGenre(genreResponse));
            }
        }
        return genreList;
    }

    public static Genre genreResponseToGenre(GenreResponse response){
        Genre genre=new Genre();
        genre.setId(response.getId());
        genre.setName(response.getName());
        return genre;
    }
}
