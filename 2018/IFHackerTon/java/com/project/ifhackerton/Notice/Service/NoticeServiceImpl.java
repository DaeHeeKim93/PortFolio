package com.project.ifhackerton.Notice.Service;


import com.project.ifhackerton.Main.Controller.MainController;
import com.project.ifhackerton.Mapper.NoticeMapper;
import com.project.ifhackerton.VO.Notice;
import com.project.ifhackerton.VO.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("com.project.ifhackerton.Notice.Service.NoticeServiceImpl")
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper; // HomeMapper Class 객체

    public static int pageCount = 10; // 전체 List 갯수


    // 글 카운트 조회  ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
    public int noticeListCount(){

        int page_count = noticeMapper.noticeListCount(MainController.GlobalmemberSession.getMember_idx()); // 카운트 계산
        page_count = page_count / pageCount; // 총 페이지 계산

        return page_count;

    };

	// 글 세부 내용 조회 ( 글 한개 수정, 삭제 등의 작업)
    public Notice noticeDetailSelection(int notice_idx){
        // 글 조회해서 글 한개 가져오기
        Notice NoticeOneObject = noticeMapper.noticeDetailSelection(notice_idx);
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
        List<Notice> NoticeGetListObject = noticeMapper.noticeListSelection(paging);
        return NoticeGetListObject;
    };

}
