package com.yumi.learn.controller.vo.param;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateCommand {

	@NotBlank(message = "用户名不能为空")
	private String userName;

	@NotBlank(message = "姓名不能为空")
	private String name;

	@Size(min = 11, max = 11, message = "手机号长度不符合要求")
	private String phone;

	@NotNull(message = "性别不能为空")
	private Integer sex;

}
