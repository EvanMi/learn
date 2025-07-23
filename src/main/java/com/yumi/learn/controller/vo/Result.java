package com.yumi.learn.controller.vo;

import lombok.Data;

@Data
public class Result {

	private int code;

	private String message;

	private Object data;

	public Result() {
	}

	public Result(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public static Result success(Object data) {
		return new Result(ResultCodeEnum.SUCCESS.getCode(), "成功", data);
	}

	public static Result fail(String message) {
		return new Result(ResultCodeEnum.FAIL.getCode(), message, null);
	}

	public static Result error(int code, String message) {
		return new Result(code, message, null);
	}

}
