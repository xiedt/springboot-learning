package com.laoxie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laoxie.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserInfo> {
}
