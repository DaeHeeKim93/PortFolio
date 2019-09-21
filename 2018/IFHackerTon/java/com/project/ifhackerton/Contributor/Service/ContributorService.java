package com.project.ifhackerton.Contributor.Service;



import com.project.ifhackerton.VO.Contributor;
import java.util.List;

public interface ContributorService {
    // 글 리스트 조회
    public List<Contributor> contributeListSelection(int page);

    // 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
    public int ContributorListCount();

    // 미로그인이 글 볼 수 있게 작업

    // 글 리스트 조회
    public List<Contributor> contribute_ListSelection(int page);

    // 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
    public int Contributor_ListCount();
}
