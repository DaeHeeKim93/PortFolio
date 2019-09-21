package com.project.dbboard.Mapper;

import com.project.dbboard.VO.UserVO;
import java.util.List;

/**
 *  User Map을 받아 처리해 주는 Mapper Interface
 */
public interface UserMapper {
    public List<UserVO> selectinformation();
    public void insertuser(UserVO uservo);
    public void deleteuser(String id);
}
