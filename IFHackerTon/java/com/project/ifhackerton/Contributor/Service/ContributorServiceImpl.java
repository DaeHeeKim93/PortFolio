package com.project.ifhackerton.Contributor.Service;


import com.project.ifhackerton.Main.Controller.MainController;
import com.project.ifhackerton.Mapper.ContributorMapper;
import com.project.ifhackerton.Mapper.WordMapper;
import com.project.ifhackerton.VO.Contributor;
import com.project.ifhackerton.VO.MemberSession;
import com.project.ifhackerton.VO.Paging;
import com.project.ifhackerton.VO.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("com.project.ifhackerton.Word.Service.ContributorServiceimpl")
public class ContributorServiceImpl implements ContributorService {

	@Autowired
	private ContributorMapper contributorMapper; // contributorMapper Class 객체
	public static int pageCount = 10; // 전체 List 갯수



	public List<Contributor> contributeListSelection(int page){

		// 이전 페이지 , 다음 페이지
		int pre_page   = page *  pageCount;
		int next_page  = (page+1) * pageCount - 1;
		Paging paging = new Paging();
		paging.setPrePage(pre_page);
		paging.setNextPage(next_page);
		paging.setMember_idx(MainController.GlobalmemberSession.getMember_idx());
		// 글 10개 단위의 글 객체를 가져옴
		List<Contributor> ContributorGetListObject = contributorMapper.contributeListSelection(paging);
		return ContributorGetListObject;

	};

	// 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
	public int ContributorListCount(){
		int page_count = contributorMapper.ContributorListCount(); // 카운트 계산
		page_count = page_count / pageCount; // 총 페이지 계산
		return page_count;
	}




	// 미로그인이 기여자 볼 수 있게 하기
	public List<Contributor> contribute_ListSelection(int page){

		// 이전 페이지 , 다음 페이지
		int pre_page   = page *  pageCount;
		int next_page  = (page+1) * pageCount - 1;
		Paging paging = new Paging();
		paging.setPrePage(pre_page);
		paging.setNextPage(next_page);
		// 글 10개 단위의 글 객체를 가져옴
		List<Contributor> ContributorGetListObject = contributorMapper.contributeListSelection(paging);
		return ContributorGetListObject;

	};

	// 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
	public int Contributor_ListCount(){
		int page_count = contributorMapper.ContributorListCount(); // 카운트 계산
		page_count = page_count / pageCount; // 총 페이지 계산
		return page_count;
	}
}
