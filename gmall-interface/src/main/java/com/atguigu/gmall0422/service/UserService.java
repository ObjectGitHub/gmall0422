package com.atguigu.gmall0422.service;


import com.atguigu.gmall0422.bean.UserAddress;
import com.atguigu.gmall0422.bean.UserInfo;

import java.util.List;

public interface UserService {
    //查询所有数据
    List<UserInfo> findAll();

    List<UserInfo> findByUserInfo(UserInfo userInfo);


    List<UserInfo> findByloginName(String loginName);

    void addUser(UserInfo userInfo);

    void updateUser(UserInfo userInfo);

    void delUser(UserInfo userInfo);

    /**
     * 根据用户id查询用户地址
     * @param id
     * @return
     */
    List<UserAddress> findUserAddressByUserId(String id);

    UserInfo login(UserInfo userInfo);

    UserInfo verify(String userId);
}
