package com.laoxie.controller;


import com.laoxie.entity.UserInfo;
import com.laoxie.service.UserService;
import com.laoxie.utils.ExecuteShellUtil;
import com.laoxie.utils.ScpClientUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {

    @Resource
    UserService userService;

    @RequestMapping(value = "getAllUser",method = RequestMethod.GET)
    public List<UserInfo> getUsers(){
        List<UserInfo>list = userService.getUserInfos();
        return list;
    }

    @RequestMapping(value = "export",method = RequestMethod.GET)
    public void testOutput(){

    }

    //测试连接linux服务器
    @RequestMapping(value = "testSyn",method = RequestMethod.GET)
    public void syn(){
        ExecuteShellUtil executeShellUtil = new ExecuteShellUtil("47.95.110.238","root","X1908522743.",22);
        String result = executeShellUtil.executeForResult("ls /home/aaa");
        System.out.println(result);
        executeShellUtil.executeForResult("touch /home/aaa/test.txt");
        executeShellUtil.close();
    }

    //将路径下的文件上传到Linux服务器里
    @RequestMapping(value = "testUpload",method = RequestMethod.GET)
    public void testUpload(){
        ScpClientUtil scpClient = ScpClientUtil.getInstance("47.95.110.238", 22, "root", "X1908522743.");
        scpClient.putFile("src/main/resources/static/users.dat","/home/aaa");
    }

}
