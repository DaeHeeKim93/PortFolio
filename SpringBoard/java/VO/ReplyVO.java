package com.project.dbboard.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyVO {
    private String re_idx;  // 답글 번호
    private String comment_idx; // 댓글 번호
    private String re_board_idx; // 게시글 번호
    private String re_com_nick; // 답글 닉네임
    private String re_content; // 답글 내용
    private String re_com_id; // 답글 ID
    private String re_com_regedate;// 답글 날짜

}
