package com.yumi.learn.controller.vo;

public enum ResultCodeEnum {

	// 成功
	SUCCESS(0, "成功"), FAIL(1, "失败"),

	// 自定义业务错误
	ARGUMENT_VALID_ERROR(1000, "参数校验错误"),;

	private final int code;

	private final String message;

	ResultCodeEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "ResultCodeEnum{" + "code=" + code + ", message='" + message + '\'' + '}';
	}

}
