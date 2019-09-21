package com.comu.hack.Report.Service;

import com.comu.hack.Main.Controller.MainController;
import com.comu.hack.Mapper.ReportMapper;
import com.comu.hack.VO.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;
/*
 * 제목 : BoardServiceImpl
 * 날짜 : 2018.10.05
 * 내용 : 글 쓰기, 수정, 리스트, 페이지, 삭제 등 Service Implement ( 구현체 )
 * */

@Service("com.comu.hack.Home.Service.ReportServiceImpl")
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportMapper reportMapper; // HomeMapper Class 객체
    public static int pageCount = 10; // 전체 List 갯수


	// 글 삽입 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void reportInsert(Report Report, MemberSession memberSession){

        Report.setMember_idx(memberSession.getMember_idx()); // 멤버 ID
        Report.setReport_id(memberSession.getId()); // 신고자 ID
		reportMapper.reportInsert(Report);

	};

	// 글 삭제 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void reportDelete(int report_idx, MemberSession memberSession){

	    // 내용 삭제 작업
        Report ReportDeleteObject  = reportMapper.reportDetailSelection(report_idx); // 한개의 값을 가져옵니다.

        // 세션값과 글 작성자를 비교하여 확인하여 삭제한다.
        if(ReportDeleteObject.getMember_idx() == memberSession.getMember_idx()){
            reportMapper.reportDelete(report_idx);
        }

	};

	// 글 수정 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void reportUpdate(Report Report, MemberSession memberSession){

	    // 글 수정시 작성자와 일치하는지 확인
        int Report_Number = Report.getReport_idx(); // 글 수정 작성자가 글 수정을 하는지 확인을 위해 Idx를 가져온다.
        Report ReportUpdateObject = reportMapper.reportDetailSelection(Report_Number);

        // 세션값과 글 작성자를 비교하여 확인하여 수정한다.
        if(ReportUpdateObject.getMember_idx() == memberSession.getMember_idx()){
            reportMapper.reportUpdate(Report);
        }

	};

    // 글 카운트 조회  ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
    public int reportListCount(){

        int page_count = reportMapper.reportListCount(MainController.GlobalmemberSession.getMember_idx()); // 카운트 계산
        page_count = page_count / pageCount; // 총 페이지 계산
        return page_count;

    };

	// 글 세부 내용 조회 ( 글 한개 수정, 삭제 등의 작업)
    public Report reportDetailSelection(int report_idx){

        // 글 조회해서 글 한개 가져오기
        Report ReportOneObject = reportMapper.reportDetailSelection(report_idx);
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
        List<Report> ReportGetListObject = reportMapper.reportListSelection(paging);
        return ReportGetListObject;

    };

}
