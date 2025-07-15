package com.yumi.learn.service.api;

import com.yumi.learn.controller.vo.validated.group.UserGroup;
import com.yumi.learn.service.param.UserParam;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
/**
 * (1 spring校验相关)
 * */
@Service
@Validated
public class UserUpdateService1 {

    @Validated(UserGroup.UpdateGroup.class)
    public String updateUser(@Valid UserParam param) {
        return "service 校验通过";
    }
}
