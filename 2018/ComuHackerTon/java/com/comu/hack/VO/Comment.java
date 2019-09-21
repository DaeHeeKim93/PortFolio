package com.comu.hack.VO;

/*
 * 제목 : Comment
 * 날짜 : 2018.10.06
 * 내용 : 댓글 VO
 * */

public class Comment {
    int comment_idx;
    String comment_context;  // 댓글 내용
    String comment_date; // 신고일
    String comment_id; // 신고자
    int isDel; // 삭제 여부
    int member_idx; // 신고자 idx
    String board_id; // 글 작성자
    int board_idx; // 글 idx


    public int getComment_idx() {
        return comment_idx;
    }
    public void setComment_idx(int comment_idx) {
        this.comment_idx = comment_idx;
    }
    public String getComment_context() {
        return comment_context;
    }
    public void setComment_context(String comment_context) {
        this.comment_context = comment_context;
    }
    public String getComment_date() {
        return comment_date;
    }
    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }
    public String getComment_id() {
        return comment_id;
    }
    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }
    public int getIsDel() {
        return isDel;
    }
    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }
    public int getMember_idx() {
        return member_idx;
    }
    public void setMember_idx(int member_idx) {
        this.member_idx = member_idx;
    }
    public int getBoard_idx() {
        return board_idx;
    }
    public void setBoard_idx(int board_idx) {
        this.board_idx = board_idx;
    }
    public String getBoard_id() {
        return board_id;
    }
    public void setBoard_id(String board_id) {
        this.board_id = board_id;
    }
}
