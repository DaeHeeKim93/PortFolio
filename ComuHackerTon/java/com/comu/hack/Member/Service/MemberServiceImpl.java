package com.comu.hack.Member.Service;


import com.comu.hack.Main.Controller.MainController;
import com.comu.hack.Mapper.MemberMapper;
import com.comu.hack.VO.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.applet.Main;

import java.util.List;

/*
 * 제목 : AdminService
 * 날짜 : 2018.08.21
 * 내용 : 회원가입, 로그인 관리 ServiceImp; 객체
 * */

@Service("com.comu.hack.Home.Service.MemberServiceImpl")
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
	// 신고내역 출력
	public List<Report> ReportKill_ListSelection(int member_idx){
		List<Report> report_Kill = memberMapper.Report_ListSelection(MainController.GlobalmemberSession.getMember_idx());
		return report_Kill;
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


}
