package com.yumi.learn.controller.argument.resolver;

import com.yumi.learn.common.value.Version;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * (3 多版本API)
 */
public class VersionHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	private static final Logger LOGGER = LoggerFactory.getLogger(VersionHandlerMethodArgumentResolver.class);

	private static final Pattern VERSION_PATTERN = Pattern.compile("application/vnd\\.(.+)\\.(.+)\\+json");

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 判断参数类型是否是 User 类型
		return parameter.getParameterType().equals(Version.class);
	}

	@Override
	public Object resolveArgument(@Nullable MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
		String accept = webRequest.getHeader("Accept");
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("accept: {}", accept);
		}
		Assert.hasText(accept, "Accept header is required");
		Matcher matcher = VERSION_PATTERN.matcher(accept);
		if (matcher.find()) {
			String vendor = matcher.group(1);
			if (!Objects.equals("yumi", vendor)) {
				throw new IllegalArgumentException("Accept header is not valid");
			}
			String version = matcher.group(2);
			LOGGER.info("vendor: {}, version: {}", vendor, version);
			return Version.ofCode(version);
		}
		throw new IllegalArgumentException("Accept header is not valid");
	}

}
