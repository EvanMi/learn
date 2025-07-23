package com.yumi.learn.controller.api;

import com.yumi.learn.common.value.User;
import com.yumi.learn.common.value.Version;
import com.yumi.learn.controller.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * (3 多版本API)
 * */
@RestController
@RequestMapping("/version")
public class RestfulVersionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestfulVersionController.class);


    /***
     * <type>/<tree>.<subtype>+<suffix>[;参数…]
     * 1. type：application、audio、example、image、message、model、multipart、text、video
     * 2. tree：vnd
     * 3. subtype：yumi.v[x]
     * 4. suffix：json
     * 5. 参数：charset=utf-8
     */
    @GetMapping(value = "/users/{userId}", produces = {
           Version.v1VersionAcceptStr, Version.v2VersionAcceptStr
    })
    public Result getUserInfo(@PathVariable("userId") String userId, Version version) {
        LOGGER.info("v1-v2-version: {}", version);
        return Result.success(new User(userId));
    }

    @GetMapping(value = "/users/{userId}", produces = Version.v3VersionAcceptStr)
    public Result getUserInfoV3(@PathVariable("userId") String userId, Version version) {
        LOGGER.info("v3-version: {}", version);
        return Result.success(new User(userId));
    }
}
