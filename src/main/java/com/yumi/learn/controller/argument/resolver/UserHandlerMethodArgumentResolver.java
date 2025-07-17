package com.yumi.learn.controller.argument.resolver;

import com.yumi.learn.common.value.User;
import jakarta.annotation.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
/**
 * (2 自定义 HandlerMethodArgumentResolver)
 * */
public class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 判断参数类型是否是 User 类型
        return parameter.getParameterType().equals(User.class)
                && parameter.hasParameterAnnotation(UserAnnotation.class);
    }

    @Override
    public Object resolveArgument(@Nullable MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        // 从请求头中获取 X-User-Id
        String userId = webRequest.getHeader("X-User-Id");
        if (userId != null) {
            return new User(userId);
        }
        return null;
    }
}
