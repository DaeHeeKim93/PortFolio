package com.comu.hack.Mapper;


import com.comu.hack.VO.Notice;
import com.comu.hack.VO.Paging;

import java.util.List;

/*
* 제목 : NoticeMapper
* 날짜 : 2018.10.05
* 내용 : 공지사항 관리
* 내용 : 공지사항에 대한 Mapping
* */

public interface NoticeMapper {


	// 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
	public int noticeListCount(int member_idx);

	// 글 내용 세부 조회
	public Notice noticeDetailSelection(int notice_idx);

	// 글 리스트 조회
	public List<Notice> noticeListSelection(Paging paging);

}
