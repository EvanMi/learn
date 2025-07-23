package com.yumi.learn.controller.vo.param;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserUpdateCommand {

	@NotBlank(message = "id不能为空")
	private String id;

	@NotBlank(message = "用户名不能为空")
	private String userName;

	@NotBlank(message = "姓名不能为空")
	private String name;

	@Size(min = 11, max = 11, message = "手机号长度不符合要求")
	private String phone;

	@NotNull(message = "性别不能为空")
	private Integer sex;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

}
