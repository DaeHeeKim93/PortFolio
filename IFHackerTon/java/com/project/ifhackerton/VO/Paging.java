package com.project.ifhackerton.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class Paging {

    public int PrePage; // 이전 페이지
    public int NextPage; // 다음 페이지
    public int member_idx; // 비밀글 용도

}
