package com.yumi.learn.service.api;

import com.yumi.learn.common.validator.value.Role;
import com.yumi.learn.service.param.UserParam;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserUpdateServiceTest {

	private static final Logger log = LoggerFactory.getLogger(UserUpdateServiceTest.class);

	@Resource
	UserUpdateService userUpdateService;

	@Resource
	UserUpdateService1 userUpdateService1;

	@Test
	void testUpdateUser() {

		UserParam param = new UserParam();
		param.setId("1223");
		param.setUserName("aaa");
		param.setName("sean");
		param.setPhone("12345678901");
		param.setSex(18);

		Role role = new Role("asdsd");
		param.setRole(role);

		log.info("userUpdateService result: {}", userUpdateService.updateUser(param));
	}

	@Test
	void testSetId() {
		userUpdateService.setId("1");
	}

	@Test
	void testUpdateUser1() {

		UserParam param = new UserParam();
		param.setUserName("aaa");
		param.setName("sean");
		param.setPhone("12345678901");
		param.setSex(18);

		log.info("userUpdateService1 result: {}", userUpdateService1.updateUser(param));
	}

}
