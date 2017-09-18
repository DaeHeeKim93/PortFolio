package com.project.dbboard.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by anyoz on 2017-07-17.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {

    private String comment_idx; // 댓글 번호
    private String board_idx; // 게시글 번호
    private String comment_id; // 댓글 아이디
    private String comment_nickname; // 댓글 닉네임
    private String comment_redegate; // 날짜
    private String comment_content; // 내용

}
