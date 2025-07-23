package com.yumi.learn.filter;


import com.yumi.learn.common.value.Version;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;

import java.io.IOException;
/**
 * (3 多版本API)
 * */
public class CustomHeaderFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String existingAcceptHeader = httpRequest.getHeader("Accept");
        Version.VersionEnum currentVersionEnum;
        if (!StringUtils.hasText(existingAcceptHeader) //没有传Accept
                || "*/*".equals(existingAcceptHeader) //全部
                || Version.stableVersionAcceptStr.equals(existingAcceptHeader)) { // 传了Accept，stable
            // 创建自定义的请求包装器
            CustomHttpServletRequestWrapper requestWrapper = new CustomHttpServletRequestWrapper(httpRequest);
            // 添加或修改请求头
            requestWrapper.addHeader("Accept", Version.STABLE.versionEnum().getAccept());
            currentVersionEnum =  Version.STABLE.versionEnum();
            request = requestWrapper;

        } else if (Version.latestVersionAcceptStr.equals(existingAcceptHeader)) {
            // 创建自定义的请求包装器
            CustomHttpServletRequestWrapper requestWrapper = new CustomHttpServletRequestWrapper(httpRequest);
            // 添加或修改请求头
            requestWrapper.addHeader("Accept", Version.LATEST.versionEnum().getAccept());
            currentVersionEnum =  Version.LATEST.versionEnum();
            request = requestWrapper;
        } else {
            currentVersionEnum = Version.VersionEnum.fromAccept(existingAcceptHeader);
        }

        if (currentVersionEnum.getDeprecated()) {
            httpResponse.addHeader("X-Deprecated-Version", currentVersionEnum.getCode());
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
