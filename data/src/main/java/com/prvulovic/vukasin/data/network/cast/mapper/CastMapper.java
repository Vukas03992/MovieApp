package com.prvulovic.vukasin.data.network.cast.mapper;

import com.prvulovic.vukasin.data.network.cast.model.CastMemberResponse;
import com.prvulovic.vukasin.data.network.cast.model.CastResponse;
import com.prvulovic.vukasin.domain.entity.cast.CastMember;

import java.util.ArrayList;
import java.util.List;

public class CastMapper {

    public static List<CastMember> castResponseToCastMemeberList(CastResponse castResponse){
        List<CastMember> castMemberList=new ArrayList<>();
        if (castResponse.getCastMemberResponses()!=null && !castResponse.getCastMemberResponses().isEmpty()){
            for (CastMemberResponse castMemberResponse : castResponse.getCastMemberResponses()) {
                CastMember castMember=new CastMember();
                castMember.setCastId(castMemberResponse.getCastId());
                castMember.setCharacter(castMemberResponse.getCharacter());
                castMember.setCreditId(castMemberResponse.getCreditId());
                castMember.setGender(castMemberResponse.getGender());
                castMember.setId(castMemberResponse.getId());
                castMember.setName(castMemberResponse.getName());
                castMember.setOrder(castMemberResponse.getOrder());
                castMember.setProfilePath(castMemberResponse.getProfilePath());
                castMemberList.add(castMember);
            }
        }
        return castMemberList;
    }
}
