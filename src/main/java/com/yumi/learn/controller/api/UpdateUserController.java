package com.yumi.learn.controller.api;

import com.yumi.learn.controller.vo.Result;
import com.yumi.learn.controller.vo.param.UserCommand;
import com.yumi.learn.controller.vo.validated.group.UserGroup;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (1 spring校验相关)
 */
@Validated
@RestController
@RequestMapping("/user/_update")
public class UpdateUserController {

	@PutMapping
	@Validated(UserGroup.UpdateGroup.class)
	public Result updateUser(@Valid @RequestBody UserCommand userCommand) {
		return Result.success("参数校验成功");
	}

}
