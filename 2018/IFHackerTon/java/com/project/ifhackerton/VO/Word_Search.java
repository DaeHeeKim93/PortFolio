package com.project.ifhackerton.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class Word_Search {
    int word_searchidx; /* Primary key */
    int word_idx;  /* 검색 단어 idx */
    int member_idx; /* 멤버 idx */
    String search_date; /* 검색일 */
    String search_ip; /* 검색 IP */
}
