package com.comu.hack.Admin.Service;


import com.comu.hack.Main.Controller.MainController;
import com.comu.hack.Mapper.AdminMapper;
import com.comu.hack.Mapper.MemberMapper;
import com.comu.hack.Mapper.NoticeMapper;
import com.comu.hack.VO.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * 제목 : AdminService
 * 날짜 : 2018.10.06
 * 내용 : 관리자 객체
 * */

@Service("com.comu.hack.Home.Service.AdminServiceImpl")
public class AdminServiceImpl implements AdminService {
	// memberMapper 객체
	@Autowired
	private AdminMapper adminMapper;

	public static int pageCount = 10; // 전체 List 갯수f

	// 회원관리 부분 시작
	// 회원리스트 가져오기
	public List<Member> adminMemberSelect(int page){
		int pre_page   = page *  pageCount;
		int next_page  = (page+1) * pageCount - 1;
		Paging paging = new Paging();
		paging.setPrePage(pre_page);
		paging.setNextPage(next_page);
		paging.setMember_idx(MainController.GlobalmemberSession.getMember_idx());
		// 글 10개 단위의 글 객체를 가져옴
		List<Member> MemberGetListObject = adminMapper.adminMemberSelect(paging);
		return MemberGetListObject;
	};

	// 총 회원인원 가져오기
	public int adminMemberSelectCount(){
		int MemberGetCount = adminMapper.adminMemberSelectCount();
		MemberGetCount = MemberGetCount / pageCount; // 총 페이지 계산
		return MemberGetCount;
	}

	// 회원 상태 변경
	public void adminIsDelUpdate(Member Member){
		adminMapper.adminIsDelUpdate(Member);
	};

	// 회원 기관/ 개인 변경
	public void adminStatusUpdate(Member Member){
		adminMapper.adminStatusUpdate(Member);
	};

	// 회원관리 부분 끝

	// 공지사항 부분 시작


	// 글 삽입 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void noticeInsert(Notice Notice, MemberSession memberSession){

		Notice.setMember_idx(memberSession.getMember_idx()); // 멤버 ID
		Notice.setNotice_id(memberSession.getId()); // 신고자 ID
		adminMapper.noticeInsert(Notice);


	};

	// 글 삭제 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void noticeDelete(int notice_idx, MemberSession memberSession){

		// 내용 삭제 작업
		Notice NoticeDeleteObject  = adminMapper.noticeDetailSelection(notice_idx); // 한개의 값을 가져옵니다.

		// 세션값과 글 작성자를 비교하여 확인하여 삭제한다.
		if(NoticeDeleteObject.getMember_idx() == memberSession.getMember_idx()){
			adminMapper.noticeDelete(notice_idx);
		}


	};

	// 글 수정 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void noticeUpdate(Notice Notice, MemberSession memberSession){

		// 글 수정시 작성자와 일치하는지 확인
		int Notice_Number = Notice.getNotice_idx(); // 글 수정 작성자가 글 수정을 하는지 확인을 위해 Idx를 가져온다.
		Notice NoticeUpdateObject = adminMapper.noticeDetailSelection(Notice_Number);

		// 세션값과 글 작성자를 비교하여 확인하여 수정한다.
		if(NoticeUpdateObject.getMember_idx() == memberSession.getMember_idx()){
			adminMapper.noticeUpdate(Notice);
		}


	};

	// 글 카운트 조회  ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
	public int noticeListCount(){

		int page_count = adminMapper.noticeListCount(MainController.GlobalmemberSession.getMember_idx()); // 카운트 계산
		page_count = page_count / pageCount; // 총 페이지 계산

		return page_count;

	};

	// 글 세부 내용 조회 ( 글 한개 수정, 삭제 등의 작업)
	public Notice noticeDetailSelection(int notice_idx){

		// 글 조회해서 글 한개 가져오기
		Notice NoticeOneObject = adminMapper.noticeDetailSelection(notice_idx);

		return NoticeOneObject;

	};

	// 글 리스트 조회 함수
	// 페이징 방식을 이용하여 글 10개를 가져온다.
	public List<Notice> noticeListSelection(int page){

		// 이전 페이지 , 다음 페이지
		int pre_page   = page *  pageCount;
		int next_page  = (page+1) * pageCount - 1;
		Paging paging = new Paging();
		paging.setPrePage(pre_page);
		paging.setNextPage(next_page);
		paging.setMember_idx(MainController.GlobalmemberSession.getMember_idx());
		// 글 10개 단위의 글 객체를 가져옴
		List<Notice> NoticeGetListObject = adminMapper.noticeListSelection(paging);
		return NoticeGetListObject;

	};
	// 공지사항 부분 끝


	// 신고 부분 시작
	// 글 카운트 조회  ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
	public int reportListCount(){

		int page_count = adminMapper.reportListCount(MainController.GlobalmemberSession.getMember_idx()); // 카운트 계산
		page_count = page_count / pageCount; // 총 페이지 계산
		return page_count;

	};

	// 글 세부 내용 조회 ( 글 한개 수정, 삭제 등의 작업)
	public Report reportDetailSelection(int report_idx){

		// 글 조회해서 글 한개 가져오기
		Report ReportOneObject = adminMapper.reportDetailSelection(report_idx);
		return ReportOneObject;

	};

	// 글 리스트 조회 함수
	// 페이징 방식을 이용하여 글 10개를 가져온다.
	public List<Report> reportListSelection(int page){

		// 이전 페이지 , 다음 페이지
		int pre_page   = page *  pageCount;
		int next_page  = (page+1) * pageCount - 1;
		Paging paging = new Paging();
		paging.setPrePage(pre_page);
		paging.setNextPage(next_page);
		paging.setMember_idx(MainController.GlobalmemberSession.getMember_idx());
		// 글 10개 단위의 글 객체를 가져옴
		List<Report> ReportGetListObject = adminMapper.reportListSelection(paging);
		return ReportGetListObject;

	};

	// 신고 내용
	public void adminreportUpdate(Report report){
		adminMapper.adminreportUpdate(report);
	};
	// 신고 부분 끝




}
