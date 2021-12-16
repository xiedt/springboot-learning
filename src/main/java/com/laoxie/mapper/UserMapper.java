package com.laoxie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laoxie.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<UserInfo> {
    @Select("select * from " +
            "user_info " +
            "where user_name=#{name}")
    UserInfo getUserByName(String name);
}
