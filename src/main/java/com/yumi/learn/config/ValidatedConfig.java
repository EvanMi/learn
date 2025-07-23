package com.yumi.learn.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * (1 spring校验相关)
 */
@Configuration
public class ValidatedConfig {

	/**
	 * 快速返回校验器
	 */
	@Bean
	public Validator validator() {
		ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
			.configure()
			// 快速失败模式
			.failFast(true)
			.buildValidatorFactory();
		return validatorFactory.getValidator();
	}

	/**
	 * 设置快速校验，返回方法校验处理器 使用MethodValidationPostProcessor注入后，会启动自定义校验器
	 */
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
		methodValidationPostProcessor.setValidator(validator());
		return methodValidationPostProcessor;
	}

}