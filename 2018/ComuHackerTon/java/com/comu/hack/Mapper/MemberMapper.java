package com.comu.hack.Mapper;


import com.comu.hack.VO.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/*
* 제목 : MemberMaper 관리
* 날짜 : 2018.08.19
* 내용 : 회원가입, 로그인 처리
* */

public interface MemberMapper {

	// 회원가입 함수
	public void insert_member(Member param);

	// 로그인 아이디, 비밀번호 체크
	public int check_member_password( @Param("id") String id , @Param("password") String password);

 	// 세션에 담을 회원정보 가져오기
	public MemberSession create_session(@Param("id") String id , @Param("password") String password);

	// 중복 가입 검사
	public int overlap_member(@Param("id") String id );

	// 공지사항 내역 출력
	public List<Board> board_ListSelection(int member_idx);

	// 신고내역 출력
	public List<Report> Report_ListSelection(int member_idx);

	// 자유게시판 출력
	public List<Notice> Notice_ListSelection(int member_idx);

	// 회원탈퇴 함수
	public void delete_member(int member_idx);

}
