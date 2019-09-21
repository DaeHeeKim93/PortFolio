package com.comu.hack.VO;

import lombok.*;

/*
 * 제목 : Inactive_Member
 * 날짜 : 2018.08.21
 * 내용 : 휴면회원 VO
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inactive_Member {

    int inactive_member_idx;
    String id; /* 아이디 */
    String password; /* 비밀번호 */
    String name; /* 회원 이름 */
    String email; /* 이메일 */
    String tel; /* 전화번호 */
    String emergency_tel; /* 보호자 전화번호 */
    String zipcode; /* 우편번호 */
    String area_1depth; /* 특별시 , 도 - 지역 1Depth */
    String area_2depth; /* 구 등 - 지역 2Depth */
    String area_3depth; /* 동, 면 등 - 지역 3Depth */
    String status; /* 멤버 상태 ( 기관, 개인 등 ) */
    String agency; /* 개인이나 기관의 소속 팀' */
    String inactive_reason; /* varchar(500) null comment '휴면 또는 불량 이유 */
    String inactive_status; /* 휴면 회원 (I) / 불량 회원 (B) */
    int isDel; /* 삭제 여부 */

    /* Lombok 작동이 잘 안되서 Getter, Setter를 만들었습니다. */
    public int getInactive_member_idx() {
        return inactive_member_idx;
    }

    public void setInactive_member_idx(int inactive_member_idx) {
        this.inactive_member_idx = inactive_member_idx;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmergency_tel() {
        return emergency_tel;
    }

    public void setEmergency_tel(String emergency_tel) {
        this.emergency_tel = emergency_tel;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getArea_1depth() {
        return area_1depth;
    }

    public void setArea_1depth(String area_1depth) {
        this.area_1depth = area_1depth;
    }

    public String getArea_2depth() {
        return area_2depth;
    }

    public void setArea_2depth(String area_2depth) {
        this.area_2depth = area_2depth;
    }

    public String getArea_3depth() {
        return area_3depth;
    }

    public void setArea_3depth(String area_3depth) {
        this.area_3depth = area_3depth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getInactive_reason() {
        return inactive_reason;
    }

    public void setInactive_reason(String inactive_reason) {
        this.inactive_reason = inactive_reason;
    }

    public String getInactive_status() {
        return inactive_status;
    }

    public void setInactive_status(String inactive_status) {
        this.inactive_status = inactive_status;
    }


    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

}
