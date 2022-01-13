package com.laoxie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laoxie.entity.StudentInfo;
import com.laoxie.entity.UserInfo;
import com.laoxie.mapper.StudentInfoMapper;
import com.laoxie.mapper.UserMapper;
import com.laoxie.service.StudentService;
import com.laoxie.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo> implements StudentService {

    @Resource
    StudentInfoMapper studentInfoMapper;
    @Override
    public List<StudentInfo> getStudentInfo() {
        return studentInfoMapper.getStudentInfo();
    }
}
