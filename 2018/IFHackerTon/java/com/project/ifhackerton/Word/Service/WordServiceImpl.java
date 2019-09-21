package com.project.ifhackerton.Word.Service;


import com.project.ifhackerton.Main.Controller.MainController;

import com.project.ifhackerton.Mapper.WordMapper;
import com.project.ifhackerton.VO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("com.project.ifhackerton.Word.Service.WordServiceImpl")
public class WordServiceImpl implements WordService {

	@Autowired
	private WordMapper wordMapper; // HomeMapper Class 객체
    public static int pageCount = 10; // 전체 List 갯수



	// 글 삽입 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void wordInsert(Word word, MemberSession memberSession){

        word.setMember_idx(memberSession.getMember_idx()); // 멤버 ID
        word.setWord_id(memberSession.getId()); // 신고자 ID
        wordMapper.wordInsert(word);

	};

	// 북한말 출력
	public List<Word> northSearchWord(String north_textarea){
		List<Word> northSearchWord = wordMapper.northSearchWord(north_textarea);
		return northSearchWord;
	};

	// 남한말 출력
	public List<Word> southSearchWord(String south_textarea){
		List<Word> southSearchWord = wordMapper.southSearchWord(south_textarea);
		return southSearchWord;
	};

	// 북한말 -> 남한말 카운트
	public int northSearchWordCount(String north_textarea){
		int NorthCount = wordMapper.northSearchWordCount(north_textarea);
		return NorthCount;
	};

	// 남한말 -> 북한말 카운트
	public int southSearchWordCount(String south_textarea){
		int SouthCount = wordMapper.southSearchWordCount(south_textarea);
		return SouthCount;
	};
	// 검색 값 넣기
	public void SearchInsert(Word_Search word_search){
		wordMapper.SearchInsert(word_search);
	};
}
