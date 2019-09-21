package com.comu.hack.Mapper;

import com.comu.hack.VO.Member;
import com.comu.hack.VO.Notice;
import com.comu.hack.VO.Paging;
import com.comu.hack.VO.Report;

import java.util.List;

/*
 * 제목 : AdminMapper
 * 날짜 : 2018.10.06
 * 내용 : 관리자 게시판 관리
 * 내용 : 관리자게시판 대한 Mapping
 * */
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


    // 신고 관리 부분


    // 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
    public int reportListCount(int member_idx);

    // 글 내용 세부 조회
    public Report reportDetailSelection(int report_idx);

    // 글 리스트 조회
    public List<Report> reportListSelection(Paging paging);

    // 신고 내용
    public void adminreportUpdate(Report report);

    // 신고 관리 부분 종료
}
