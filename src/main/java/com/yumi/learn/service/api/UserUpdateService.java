package com.yumi.learn.service.api;

import com.yumi.learn.common.validator.PhoneNumber;
import com.yumi.learn.controller.vo.validated.group.UserGroup;
import com.yumi.learn.service.param.UserParam;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * (1 spring校验相关)
 */
@Service
@Validated(UserGroup.UpdateGroup.class)
public class UserUpdateService {

	public String updateUser(@Valid UserParam param) {
		return "service 校验通过";
	}

	public String setId(@Size(min = 2) String id) {
		return id;
	}

	public String setPhoneNumber(@PhoneNumber String phoneNumber) {
		return phoneNumber;
	}

}
