package com.laoxie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laoxie.entity.UserInfo;
import com.laoxie.mapper.UserMapper;
import com.laoxie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,UserInfo> implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public UserInfo getUserByName(String name) {
        return userMapper.getUserByName(name);
    }
}
