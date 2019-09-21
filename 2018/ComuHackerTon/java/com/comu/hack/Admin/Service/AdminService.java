package com.comu.hack.Admin.Service;

import com.comu.hack.VO.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/*
 * 제목 : AdminService
 * 날짜 : 2018.10.06
 * 내용 : 관리자 객체
 * */

public interface AdminService {

    // 회원관리 부분

    // 회원 갯수
    public List<Member> adminMemberSelect(int page);

    // 회원 카운트
    public int adminMemberSelectCount();

    // 회원 상태 변경
    public void adminIsDelUpdate(Member Member);

    // 회원 기관/ 개인 변경
    public void adminStatusUpdate(Member Member);



    // 공지사항 부분
    // 글 삽입 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
    public void noticeInsert(Notice Notice, MemberSession memberSession);

    // 글 삭제 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
    public void noticeDelete(int notice_idx, MemberSession memberSession);

    // 글 수정 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
    public void noticeUpdate(Notice Notice, MemberSession memberSession);

    // 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
    public int noticeListCount();

    // 글 세부 내용 조회 ( 글 한개)
    public Notice noticeDetailSelection(int notice_idx);

    // 글 리스트 조회
    public List<Notice> noticeListSelection(int page);



    // 신고 부분 관리
    // 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
    public int reportListCount();

    // 글 세부 내용 조회 ( 글 한개)
    public Report reportDetailSelection(int report_idx);

    // 글 리스트 조회
    public List<Report> reportListSelection(int page);

    // 신고 내용
    public void adminreportUpdate(Report report);

}
