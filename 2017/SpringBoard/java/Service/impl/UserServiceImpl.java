package com.project.dbboard.Service.impl;

import com.project.dbboard.Mapper.UserMapper;
import com.project.dbboard.Service.interf.UserService;
import com.project.dbboard.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by anyoz on 2017-07-01.
 */
@Service("com.project.dbboard.Service.impl.UserServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVO> selectUserVO() {
        // TODO Auto-generated method stub
        return userMapper.selectinformation();
    }
    @Override
    public void insertUserVO(UserVO uservo){
        userMapper.insertuser(uservo);
    };

    @Override
    public void deleteuserVO(String id){userMapper.deleteuser(id);};
}
