package com.yumi.learn.config;

import com.yumi.learn.controller.argument.resolver.UserHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        /**
         * (2 自定义 HandlerMethodArgumentResolver)
        * */
        resolvers.add(new UserHandlerMethodArgumentResolver());
    }
}
