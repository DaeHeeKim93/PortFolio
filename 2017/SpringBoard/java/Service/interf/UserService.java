package com.project.dbboard.Service.interf;

import com.project.dbboard.VO.UserVO;

import java.util.List;

/**
 * Created by anyoz on 2017-07-01.
 */
public interface UserService {
    public List<UserVO> selectUserVO();
    public void insertUserVO(UserVO uservo);
    public void deleteuserVO(String id);
}
