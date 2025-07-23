package com.yumi.learn.config;

import com.yumi.learn.filter.CustomHeaderFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

	/**
	 * (3 多版本API)
	 */
	@Bean
	public FilterRegistrationBean<CustomHeaderFilter> loggingFilter() {
		FilterRegistrationBean<CustomHeaderFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new CustomHeaderFilter());
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}

}
