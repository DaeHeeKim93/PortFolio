package com.comu.hack.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 제목 : Board
 * 날짜 : 2018.10.05
 * 내용 : 자유게시판 VO
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    int board_idx;
    String board_title; /*  제목 */
    String board_context; /*  내용 */
    String board_date; /* 신고일 */
    int   member_idx; /* 신고자  Primary Key */
    String board_id; /* 작성자 */
    int isDel; /* 삭제 여부 */
    int isSecret; /* 비밀글 여부 */

    /* Lombok 작동이 잘 안되서 Getter, Setter를 만들었습니다. */
    public int getBoard_idx() {
        return board_idx;
    }
    public void setBoard_idx(int board_idx) {
        this.board_idx = board_idx;
    }
    public String getBoard_title() {
        return board_title;
    }
    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }
    public String getBoard_context() {
        return board_context;
    }
    public void setBoard_context(String board_context) {
        this.board_context = board_context;
    }
    public String getBoard_date() {
        return board_date;
    }
    public void setBoard_date(String board_date) {
        this.board_date = board_date;
    }
    public int getMember_idx() {
        return member_idx;
    }
    public void setMember_idx(int member_idx) {
        this.member_idx = member_idx;
    }
    public String getBoard_id() {
        return board_id;
    }
    public void setBoard_id(String board_id) {
        this.board_id = board_id;
    }
    public int getIsDel() {
        return isDel;
    }
    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }
    public int getIsSecret() {
        return isSecret;
    }
    public void setIsSecret(int isSecret) {
        this.isSecret = isSecret;
    }
}
