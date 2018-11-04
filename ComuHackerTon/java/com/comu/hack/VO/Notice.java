package com.comu.hack.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 제목 : Notice
 * 날짜 : 2018.08.21
 * 내용 : 공지사항 VO
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

    public int notice_idx;
    String notice_title; /* 공지사항 제목 */
    String notice_context; /* 공지사항 내용 */
    String notice_date; /* 공지사항 발표일 */
    int    member_idx; /* 공지사항 멤버 Primary Key */
    String notice_id; /* 공지사항 작성자 */
    int isDel; /* 삭제 여부 */
    int isSecret; /* 비밀글 여부 */

    /* Lombok 작동이 잘 안되서 Getter, Setter를 만들었습니다. */

    public int getNotice_idx() {
        return notice_idx;
    }
    public void setNotice_idx(int notice_idx) {
        this.notice_idx = notice_idx;
    }
    public String getNotice_title() {
        return notice_title;
    }
    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }
    public String getNotice_context() {
        return notice_context;
    }
    public void setNotice_context(String notice_context) {
        this.notice_context = notice_context;
    }
    public String getNotice_date() {
        return notice_date;
    }
    public void setNotice_date(String notice_date) {
        this.notice_date = notice_date;
    }
    public int getMember_idx() {
        return member_idx;
    }
    public void setMember_idx(int member_idx) {
        this.member_idx = member_idx;
    }
    public String getNotice_id() {
        return notice_id;
    }
    public void setNotice_id(String notice_id) {
        this.notice_id = notice_id;
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
