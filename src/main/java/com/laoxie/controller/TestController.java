package com.laoxie.controller;

import com.laoxie.entity.RestMessage;
import com.laoxie.utils.NoRepeatSubmit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 * @author zhouzhaodong
 */
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 添加防重复提交注解
     * @return
     */
    @NoRepeatSubmit
    @RequestMapping("/one")
    public RestMessage test(){
        return new RestMessage(0, "测试通过");
    }

}