package com.project.ifhackerton.Notice.Service;


import com.project.ifhackerton.VO.Notice;
import java.util.List;

public interface NoticeService {

	// 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
	public int noticeListCount();

	// 글 세부 내용 조회 ( 글 한개)
	public Notice noticeDetailSelection(int notice_idx);

	// 글 리스트 조회
	public List<Notice> noticeListSelection(int page);

}
