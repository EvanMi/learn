package com.yumi.learn.controller.argument.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CustomArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 检查参数是否带有自定义注解
        return parameter.hasParameterAnnotation(CustomAnnotation.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 从请求中获取参数值，进行自定义解析
        String paramValue = webRequest.getParameter("customParam");
        // 可以在这里进行任何所需的转换或处理
        return processParamValue(paramValue);
    }

    private Object processParamValue(String paramValue) {
        // 自定义逻辑
        return paramValue != null ? paramValue.toUpperCase() : "DEFAULT_VALUE";
    }
}
