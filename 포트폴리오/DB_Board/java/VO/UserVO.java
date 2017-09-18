package com.project.dbboard.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private int idx;         // 회원수 관리
    private String id;       // 아이디
    private String password; // 비밀번호
    private String nickname; // 닉네임

}
