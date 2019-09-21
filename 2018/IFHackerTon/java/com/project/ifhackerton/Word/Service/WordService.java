package com.project.ifhackerton.Word.Service;



import com.project.ifhackerton.VO.MemberSession;
import com.project.ifhackerton.VO.Word;
import com.project.ifhackerton.VO.Word_Search;

import java.util.List;

public interface WordService {

	// 글 삽입 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void wordInsert(Word word, MemberSession memberSession);
	// 북한말 출력
	public List<Word> northSearchWord(String north_textarea);

	// 남한말 출력
	public List<Word> southSearchWord(String south_textarea);

	// 북한말 -> 남한말 카운트
	public int northSearchWordCount(String north_textarea);

	// 남한말 -> 북한말 카운트
	public int southSearchWordCount(String south_textarea);

	// 검색 값 집어넣기
	public void SearchInsert(Word_Search word_search);
}
