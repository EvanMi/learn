package com.yumi.learn.controller.api;

import com.yumi.learn.controller.vo.Result;
import com.yumi.learn.controller.vo.param.UserCommand;
import com.yumi.learn.controller.vo.param.UserCreateCommand;
import com.yumi.learn.controller.vo.param.UserUpdateCommand;
import com.yumi.learn.controller.vo.validated.group.UserGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * (1 spring校验相关)
 * */
//@Validated
@RestController
@RequestMapping("/user")
public class UserController {

	/**
	 * 注解 @Valid的触发时机是在HandlerMethodArgumentResolver这个组件封装解析参数是触发
	 */
	@PostMapping("create")
	public Result createUser(@Valid @RequestBody UserCreateCommand userCreateCommand) {
		return Result.success("参数校验成功");
	}

	/**
	 * 注解 @Validated如果在Service层中使用，是AOP机制，
	 * 通过 MethodValidationInterceptorAOP 代理在方法调用是触发
	 * 注解@Validated 如果只是在Controller层方法中使用，
	 * Spring MVC 会将其视为 @Valid 的增强版，仍通过 RequestResponseBodyMethodProcessor 触发校验
	 */
	@PostMapping("update")
	public Result updateUser(@Validated @RequestBody UserUpdateCommand userUpdateCommand) {
		return Result.success("参数校验成功");
	}

	@PostMapping("common/create")
	public Result createUser(@Validated(UserGroup.CreateGroup.class) @RequestBody UserCommand userCommand) {
		return Result.success("参数校验成功");
	}

	@PostMapping("common/update")
	public Result updateUser(@Validated(UserGroup.UpdateGroup.class) @RequestBody UserCommand userCommand) {
		return Result.success("参数校验成功");
	}

	@GetMapping("getUserById/{id}")
	public Result getUserById(@PathVariable @Size(min = 2, max = 5, message = "id长度不符合要求") String id) {
		return Result.success("参数校验成功");
	}

}
