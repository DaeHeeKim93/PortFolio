package com.project.ifhackerton.Mapper;

import com.project.ifhackerton.VO.Word;
import com.project.ifhackerton.VO.Word_Search;

import java.util.List;

public interface WordMapper {

    // 단어 삽입
    public void wordInsert(Word Word);

    // 북한말 출력
    public List<Word> northSearchWord(String north_textarea);

    // 남한말 출력
    public List<Word> southSearchWord(String south_textarea);


    // 북한말 -> 남한말 카운트
    public int northSearchWordCount(String north_textarea);

    // 남한말 -> 북한말 카운트
    public int southSearchWordCount(String south_textarea);

    // 검색 조건 값 집어넣기
    public void SearchInsert(Word_Search word_search);


}
