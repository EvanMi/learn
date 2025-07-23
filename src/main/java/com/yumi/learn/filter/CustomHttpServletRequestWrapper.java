package com.yumi.learn.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * (3 多版本API)
 */
public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private final Map<String, String> customHeaders;

	public CustomHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.customHeaders = new HashMap<>();
	}

	public void addHeader(String name, String value) {
		this.customHeaders.put(name, value);
	}

	@Override
	public String getHeader(String name) {
		// 如果自定义头中有该头部，则返回自定义头部的值
		String headerValue = customHeaders.get(name);
		if (headerValue != null) {
			return headerValue;
		}
		// 否则返回原始请求头的值
		return ((HttpServletRequest) getRequest()).getHeader(name);
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		// 合并自定义头和原始请求头
		HashMap<String, String> combinedHeaders = new HashMap<>(customHeaders);
		Enumeration<String> originalHeaderNames = ((HttpServletRequest) getRequest()).getHeaderNames();
		while (originalHeaderNames.hasMoreElements()) {
			String headerName = originalHeaderNames.nextElement();
			combinedHeaders.putIfAbsent(headerName, ((HttpServletRequest) getRequest()).getHeader(headerName));
		}
		return Collections.enumeration(combinedHeaders.keySet());
	}

	@Override
	public Enumeration<String> getHeaders(String name) {
		// 如果自定义头中有该头部，则返回一个包含该头部的Enumeration
		if (customHeaders.containsKey(name)) {
			return Collections.enumeration(Collections.singletonList(customHeaders.get(name)));
		}
		// 否则返回原始请求头中的值
		return ((HttpServletRequest) getRequest()).getHeaders(name);
	}

}
