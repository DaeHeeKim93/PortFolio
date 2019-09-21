package com.comu.hack.VO;

/*
 * 제목 : Report
 * 날짜 : 2018.10.05
 * 내용 : 전체 범죄관리 VO
 * */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    int report_idx; /* 레포트 고유키 */
    String report_division; /* 범죄 분류 - 한국어 */
    String report_en_divison; /* 범죄 분류 - 영어 */
    String report_title; /* 신고 제목 */
    String report_context; /* 신고 내용 */
    String report_date; /* 신고일 */
    String solution_date; /* 해결일 */
    int    member_idx; /* 신고자  Primary Key */
    String report_id; /* 신고자 */
    String clear_id; /* 해결자 */
    String report_status; /* 신고 해결 유무 */
    int isDel; /* 삭제 여부 */
    int isSecret; /* 비밀글 여부 */

    public int getReport_idx() {
        return report_idx;
    }
    public void setReport_idx(int report_idx) {
        this.report_idx = report_idx;
    }
    public String getReport_division() {
        return report_division;
    }
    public void setReport_division(String report_division) {
        this.report_division = report_division;
    }
    public String getReport_en_divison() {
        return report_en_divison;
    }
    public void setReport_en_divison(String report_en_divison) {
        this.report_en_divison = report_en_divison;
    }
    public String getReport_title() {
        return report_title;
    }
    public void setReport_title(String report_title) {
        this.report_title = report_title;
    }
    public String getReport_context() {
        return report_context;
    }
    public void setReport_context(String report_context) {
        this.report_context = report_context;
    }
    public String getReport_date() {
        return report_date;
    }
    public void setReport_date(String report_date) {
        this.report_date = report_date;
    }
    public String getSolution_date() {
        return solution_date;
    }
    public void setSolution_date(String solution_date) {
        this.solution_date = solution_date;
    }
    public int getMember_idx() {
        return member_idx;
    }
    public void setMember_idx(int member_idx) {
        this.member_idx = member_idx;
    }
    public String getReport_id() {
        return report_id;
    }
    public void setReport_id(String report_id) {
        this.report_id = report_id;
    }
    public String getClear_id() {
        return clear_id;
    }
    public void setClear_id(String clear_id) {
        this.clear_id = clear_id;
    }
    public String getReport_status() {
        return report_status;
    }
    public void setReport_status(String report_status) {
        this.report_status = report_status;
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
