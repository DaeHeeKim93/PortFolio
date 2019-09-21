package com.project.ifhackerton.Member.Service;


import com.project.ifhackerton.VO.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberService {

	// 회원가입 함수
	public void insert_member(Member param);

	// 로그인 아이디, 비밀번호 체크
	public int check_member_password(@Param("id") String id, @Param("password") String password);

	// 세션에 담을 정보 가져오기
	public MemberSession create_session(@Param("id") String id, @Param("password") String password);

	//공지사항 내역 출력
	public List<Board> board_ListSelection(int member_idx);

	// 자유게시판 출력
	public List<Notice> Notice_ListSelection(int member_idx);

	// 회원탈퇴
	public void delete_member(int member_idx);

	//중복가입 검사
	public int overlap_member(@Param("id") String id);

	// 로그인, 로그아웃 접속
	public void MemberLog(MemberLog memberLog);

	// 단어 내역 출력
	public List<Word> Word_ListSelection();

}
