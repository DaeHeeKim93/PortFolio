package com.project.ifhackerton.Mapper;


import com.project.ifhackerton.VO.Notice;
import com.project.ifhackerton.VO.Paging;
import java.util.List;


public interface NoticeMapper {


	// 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
	public int noticeListCount(int member_idx);

	// 글 내용 세부 조회
	public Notice noticeDetailSelection(int notice_idx);

	// 글 리스트 조회
	public List<Notice> noticeListSelection(Paging paging);

}
