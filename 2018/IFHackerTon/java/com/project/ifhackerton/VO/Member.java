package com.project.ifhackerton.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class Member {
    int member_idx;
    String id; /*아이디 */
    String password; /* 비밀번호 */
    String name; /* 회원 이름 */
    String email; /* 이메일 */
    String tel; /* 전화번호 */
    String zipcode; /* 우편번호 */
    String status; /* 멤버 상태 ( 기관, 개인 등 ) */
    String agency; /* 개인이나 기관의 소속 팀' */
    int isDel; /* 삭제 여부 */

}
