package com.project.ifhackerton.Member.Service;



import com.project.ifhackerton.Main.Controller.MainController;
import com.project.ifhackerton.Mapper.MemberMapper;
import com.project.ifhackerton.VO.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service("com.project.ifhackerton.Service.MemberServiceImpl")
public class MemberServiceImpl implements MemberService {
	// memberMapper 객체
	@Autowired
	private MemberMapper memberMapper;

	// 회원가입 함수
	public void insert_member(Member param) {
		memberMapper.insert_member(param);
	}

	// 로그인 아이디, 비밀번호 체크
	public int check_member_password(@Param("id") String id , @Param("password") String password){
		return memberMapper.check_member_password(id,password);
	};

	// 세션에 담을 회원정보 가져오기
	public MemberSession create_session(@Param("id") String id , @Param("password") String password){
		return memberMapper.create_session(id,password);
	};

	// 공지사항 내역 출력
	public List<Board> board_ListSelection(int member_idx){
		List<Board> boardlist = memberMapper.board_ListSelection(MainController.GlobalmemberSession.getMember_idx());
		return boardlist;
	};


	// 자유게시판 출력
	public List<Notice> Notice_ListSelection(int member_idx){
		List<Notice> Noticelist = memberMapper.Notice_ListSelection(MainController.GlobalmemberSession.getMember_idx());
		return Noticelist;
	};
	// 회원탈퇴
	public void delete_member(int member_idx){
		memberMapper.delete_member(MainController.GlobalmemberSession.getMember_idx());
	};

	// 중복가입 검사
	public int overlap_member(@Param("id") String id ){
		int overLapCount = memberMapper.overlap_member(id);
		return overLapCount;
	};

	// 로그인, 로그아웃 접속
	public void MemberLog(MemberLog memberLog){
		memberMapper.MemberLog(memberLog);
	};

	// 단어 내역 출력
	public List<Word> Word_ListSelection(){
		List<Word> WordList = memberMapper.Word_ListSelection();
		return WordList;
	};

}
