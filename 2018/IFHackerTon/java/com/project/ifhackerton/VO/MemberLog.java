package com.project.ifhackerton.VO;

import lombok.*;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Getter
@Setter
public class MemberLog {
    int memberlogidx; // 멤버 idx
    String login_ip; // 멤버 IP
    int member_idx; // 멤버 idx
    String member_id; // 멤버 ID
    String loginDate; // 접속 시간
    String status; // L - 로그인 / O - 로그아웃
}
