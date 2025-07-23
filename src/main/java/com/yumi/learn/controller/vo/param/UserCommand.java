package com.yumi.learn.controller.vo.param;

import com.yumi.learn.common.validator.PhoneNumber;
import com.yumi.learn.controller.vo.validated.group.UserGroup;
import com.yumi.learn.common.validator.value.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserCommand {

	@NotBlank(message = "id不能为空", groups = UserGroup.UpdateGroup.class)
	private String id;

	@NotBlank(message = "用户名不能为空", groups = { UserGroup.CreateGroup.class, UserGroup.UpdateGroup.class })
	private String userName;

	@NotBlank(message = "姓名不能为空", groups = { UserGroup.CreateGroup.class, UserGroup.UpdateGroup.class })
	private String name;

	@PhoneNumber(groups = { UserGroup.CreateGroup.class, UserGroup.UpdateGroup.class })
	private String phone;

	@NotNull(message = "性别不能为空", groups = { UserGroup.CreateGroup.class, UserGroup.UpdateGroup.class })
	private Integer sex;

	@Valid
	@NotNull(message = "角色信息不能为空")
	private Role role;

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
