package com.yumi.learn.controller.api;

import com.yumi.learn.common.value.User;
import com.yumi.learn.controller.argument.resolver.UserAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * (2 自定义 HandlerMethodArgumentResolver)
 * */
@RestController
@RequestMapping("/arg/resolver")
public class UserHandlerMethodArgumentResolverController {
    private static final Logger logger = LoggerFactory.getLogger(UserHandlerMethodArgumentResolverController.class);

    @GetMapping("user")
    public void getUserInfo(@UserAnnotation User user) {
        logger.info("user: {}", user);
    }
}
