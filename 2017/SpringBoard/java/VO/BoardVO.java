package com.project.dbboard.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
    private String idx; // 게시글 번호
    private String board_id; // 아이디
    private String board_nickname; // 닉네임
    private String title; // 제목
    private String redegate; // 날짜
    private int board_count; // 조회수
    private String content; // 내용
    private String filename; // 파일이름
}