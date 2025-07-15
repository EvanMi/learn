package com.yumi.learn.controller.api;

import com.yumi.learn.controller.vo.Result;
import com.yumi.learn.service.api.UserUpdateService;
import com.yumi.learn.service.param.UserParam;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * (1 spring校验相关)
 * */
@RestController
@RequestMapping("/user/service")
public class UserServiceController {

    @Resource
    private UserUpdateService userUpdateService;

    @GetMapping
    public Result updateUser() {

        UserParam param = new UserParam();
        param.setUserName("aaa");
        param.setName("sean");
        param.setPhone("12345678901");
        param.setSex(18);

        userUpdateService.updateUser(param);
        return Result.success("参数校验成功");
    }
}
