package com.comu.hack.Notice.Service;

import com.comu.hack.VO.MemberSession;
import com.comu.hack.VO.Notice;


import java.util.List;
/*
 * 제목 : NoticeService
 * 날짜 : 2018.10.05
 * 내용 : 공지사항 글 쓰기 Service 객체
 * */

public interface NoticeService {

	// 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
	public int noticeListCount();

	// 글 세부 내용 조회 ( 글 한개)
	public Notice noticeDetailSelection(int notice_idx);

	// 글 리스트 조회
	public List<Notice> noticeListSelection(int page);

}
