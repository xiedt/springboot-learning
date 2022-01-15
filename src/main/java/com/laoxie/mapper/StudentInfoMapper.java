package com.laoxie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laoxie.entity.StudentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface StudentInfoMapper extends BaseMapper<StudentInfo> {

    List<StudentInfo> getStudentInfo();
}
