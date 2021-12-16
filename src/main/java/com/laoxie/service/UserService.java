package com.laoxie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laoxie.entity.UserInfo;

public interface UserService extends IService<UserInfo> {

    UserInfo getUserByName(String name);
}
