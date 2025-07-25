package com.yumi.learn.controller.adivce;

import com.yumi.learn.controller.vo.Result;
import com.yumi.learn.controller.vo.ResultCodeEnum;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

/**
 * (1 spring校验相关)
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * spring mvc 在参数绑定时进行的校验 需要给参数加上 @Valid 或 @Validated
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public Result error(MethodArgumentNotValidException e) {
		log.info("MethodArgumentNotValidException, {}", e.getMessage());
		return Result.error(ResultCodeEnum.ARGUMENT_VALID_ERROR.getCode(),
				e.getBindingResult().getFieldError().getDefaultMessage());
	}

	// 参数校验异常
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public Result error(ConstraintViolationException e) {
		log.info("ConstraintViolationException, {}", e.getMessage());
		StringBuilder sb = new StringBuilder();
		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
			sb.append(violation.getMessage());
		}
		return Result.error(ResultCodeEnum.ARGUMENT_VALID_ERROR.getCode(), sb.toString());
	}

	@ExceptionHandler(HandlerMethodValidationException.class)
	@ResponseBody
	public Result error(HandlerMethodValidationException e) {
		log.info("HandlerMethodValidationException, {}", e.getMessage());
		StringBuilder sb = new StringBuilder();

		for (ParameterValidationResult parameterValidationResult : e.getParameterValidationResults()) {
			for (MessageSourceResolvable resolvableError : parameterValidationResult.getResolvableErrors()) {
				sb.append(resolvableError.getDefaultMessage()).append(" ");
			}
		}
		return Result.error(ResultCodeEnum.ARGUMENT_VALID_ERROR.getCode(), sb.toString());
	}

	// 参数校验异常
	@ExceptionHandler(BindException.class)
	@ResponseBody
	public Result error(BindException e) {
		log.info("BindException, {}", e.getMessage());
		StringBuilder sb = new StringBuilder();
		for (ObjectError error : e.getBindingResult().getAllErrors()) {
			sb.append(error.getDefaultMessage());
		}
		return Result.error(ResultCodeEnum.ARGUMENT_VALID_ERROR.getCode(), sb.toString());
	}

	// 全局异常
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result error(Exception e) {
		log.warn("Exception, {}", e.getMessage());
		return Result.fail(e.getMessage());
	}

}
