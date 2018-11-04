package com.comu.hack.Mapper;


import com.comu.hack.VO.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
* 제목 : ReportMapper
* 날짜 : 2018.10.05
* 내용 : 신고글 관리
* 내용 : 신고글에 대한 Mapping
* */

public interface ReportMapper {

	// 글 삽입 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void reportInsert(Report Report);

	// 글 삭제 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void reportDelete(int report_idx);

	// 글 수정 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void reportUpdate(Report Report);

	// 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
	public int reportListCount(int member_idx);

	// 글 내용 세부 조회
	public Report reportDetailSelection(int report_idx);

	// 글 리스트 조회
	public List<Report> reportListSelection(Paging paging);

}
