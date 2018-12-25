package com.project.ifhackerton.Mapper;

import com.project.ifhackerton.VO.Contributor;
import com.project.ifhackerton.VO.Paging;

import java.util.List;

public interface ContributorMapper {

    // 글 리스트 조회
    public List<Contributor> contributeListSelection(Paging paging);


    // 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
    public int ContributorListCount();


    // 글 리스트 조회
    public List<Contributor> contribute_ListSelection(int page);

    // 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
    public int Contributor_ListCount();
}
