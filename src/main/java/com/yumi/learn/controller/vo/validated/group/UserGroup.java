package com.yumi.learn.controller.vo.validated.group;

import jakarta.validation.groups.Default;

public class UserGroup {

	// 定义一个用于创建用户的校验分组
	public interface CreateGroup extends Default {

	}

	// 定义一个用于更新用户的校验分组
	public interface UpdateGroup extends Default {

	}

}