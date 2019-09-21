package com.project.ifhackerton.Mapper;


import com.project.ifhackerton.VO.*;

import java.util.List;

public interface AdminMapper {
    // 회원관리 부분

    // 회원리스트 불러오기
    public List<Member> adminMemberSelect(Paging page);

    // 회원 페이지 카운트
    public int adminMemberSelectCount();

    // 회원 상태 변경
    public void adminIsDelUpdate(Member Member);

    // 회원 기관/ 개인 변경
    public void adminStatusUpdate(Member Member);

    // 회원관리 부분 끝


    // 공지사항 부분
    // 글 삽입 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
    public void noticeInsert(Notice Notice);

    // 글 삭제 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
    public void noticeDelete(int notice_idx);

    // 글 수정 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
    public void noticeUpdate(Notice Notice);

    // 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
    public int noticeListCount(int member_idx);

    // 글 내용 세부 조회
    public Notice noticeDetailSelection(int notice_idx);

    // 글 리스트 조회
    public List<Notice> noticeListSelection(Paging paging);

    // 공지사항 부분 끝

    //단어 부분 시작

    // 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
    public int wordListCount(int member_idx);

    // 글 내용 세부 조회
    public Word wordDetailSelection(int word_idx);

    // 글 리스트 조회
    public List<Word> wordListSelection(Paging paging);

    // 단어 검색 내용
    public void adminwordUpdate(Word word);

    // 단어 부분 끝

    // 기여자 부분
    // 글 리스트 조회
    public List<Contributor> contributeListSelection(Paging paging);


    // 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
    public int ContributorListCount();

    // 통계 로그인 부분
    public List<Integer> statistics_Login();

    // 통계 메시지 부분
    public List<String> statistics_Message();
    public List<Integer> statistics_Message_Count();
}
