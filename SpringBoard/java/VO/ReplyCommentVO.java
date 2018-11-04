package com.project.dbboard.VO;

/**
 * Created by anyoz on 2017-07-28.
 */
public class ReplyCommentVO {

    private String reply_comment_idx; // 답글 번호
    private String reply_board_idx; // 게시글 번호
    private String reply_comment_id; // 댓글 아이디
    private String reply_comment_nickname; // 댓글 닉네임
    private String reply_comment_redegate; // 날짜
    private String reply_comment_content; // 내용
    private String comment_idx; // 원래 댓글 번호

    public String getReply_comment_idx() {
        return reply_comment_idx;
    }
    public void setReply_comment_idx(String reply_comment_idx) {
        this.reply_comment_idx = reply_comment_idx;
    }
    public String getReply_board_idx() {
        return reply_board_idx;
    }
    public void setReply_board_idx(String reply_board_idx) {
        this.reply_board_idx = reply_board_idx;
    }
    public String getReply_comment_id() {
        return reply_comment_id;
    }
    public void setReply_comment_id(String reply_comment_id) {
        this.reply_comment_id = reply_comment_id;
    }
    public String getReply_comment_nickname() {
        return reply_comment_nickname;
    }
    public void setReply_comment_nickname(String reply_comment_nickname) {
        this.reply_comment_nickname = reply_comment_nickname;
    }
    public String getReply_comment_redegate() {
        return reply_comment_redegate;
    }
    public void setReply_comment_redegate(String reply_comment_redegate) {
        this.reply_comment_redegate = reply_comment_redegate;
    }
    public String getReply_comment_content() {
        return reply_comment_content;
    }
    public void setReply_comment_content(String reply_comment_content) {
        this.reply_comment_content = reply_comment_content;
    }
    public String getComment_idx() {
        return comment_idx;
    }
    public void setComment_idx(String comment_idx) {
        this.comment_idx = comment_idx;
    }


}
