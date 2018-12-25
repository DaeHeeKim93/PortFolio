package com.project.ifhackerton.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class Notice {

    public int notice_idx;
    String notice_title; /* 공지사항 제목 */
    String notice_context; /* 공지사항 내용 */
    String notice_date; /* 공지사항 발표일 */
    int    member_idx; /* 공지사항 멤버 Primary Key */
    String notice_id; /* 공지사항 작성자 */
    int isDel; /* 삭제 여부 */
    int isSecret; /* 비밀글 여부 */
}
