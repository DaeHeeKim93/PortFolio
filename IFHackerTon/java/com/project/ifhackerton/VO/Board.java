package com.project.ifhackerton.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class Board {

    int board_idx;
    String board_title; /*  제목 */
    String board_context; /*  내용 */
    String board_date; /* 작성일 */
    int   member_idx; /* 신고자  Primary Key */
    String board_id; /* 작성자 */
    int isDel; /* 삭제 여부 */
    int isSecret; /* 비밀글 여부 */

}
