package com.project.ifhackerton.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class Word {
    int word_idx;
    String north_word;
    String south_word;
    String word_id;
    String word_date;
    int isDel;
    int member_idx;
    int isSecret;

}
