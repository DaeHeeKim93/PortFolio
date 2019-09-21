package com.comu.hack.VO;
/*
 * 제목 : MemberSession
 * 날짜 : 2018.08.24
 * 내용 : 페이징 VO
 * */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paging {

    public int PrePage; // 이전 페이지
    public int NextPage; // 다음 페이지
    public int member_idx; // 비밀글 용도

    public int getPrePage() {
        return PrePage;
    }
    public void setPrePage(int prePage) {
        PrePage = prePage;
    }
    public int getNextPage() {
        return NextPage;
    }
    public void setNextPage(int nextPage) {
        NextPage = nextPage;
    }
    public int getMember_idx() {
        return member_idx;
    }
    public void setMember_idx(int member_idx) {
        this.member_idx = member_idx;
    }

}
