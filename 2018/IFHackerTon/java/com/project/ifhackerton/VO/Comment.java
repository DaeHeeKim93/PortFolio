package com.project.ifhackerton.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class Comment {
    int comment_idx;
    String comment_context;  // 댓글 내용
    String comment_date; // 작성일
    String comment_id; // 작성자
    int isDel; // 삭제 여부
    int member_idx; // 작성자 idx
    String board_id; // 글 작성자
    int board_idx; // 글 idx
}
