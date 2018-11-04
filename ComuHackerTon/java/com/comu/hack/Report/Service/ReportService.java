package com.comu.hack.Report.Service;

import com.comu.hack.VO.*;

import java.util.List;
/*
 * 제목 : BoardService
 * 날짜 : 2018.08.24
 * 내용 : 글 쓰기 Service 객체
 * */

public interface ReportService {

	// 글 삽입 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void reportInsert(Report Report, MemberSession memberSession);

	// 글 삭제 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void reportDelete(int Report, MemberSession memberSession);

	// 글 수정 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void reportUpdate(Report Report, MemberSession memberSession);

	// 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
	public int reportListCount();

	// 글 세부 내용 조회 ( 글 한개)
	public Report reportDetailSelection(int report_idx);

	// 글 리스트 조회
	public List<Report> reportListSelection(int page);

}
