package com.laoxie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laoxie.entity.StudentInfo;

import java.util.List;

public interface StudentService extends IService<StudentInfo> {

    List<StudentInfo> getStudentInfo();
}
