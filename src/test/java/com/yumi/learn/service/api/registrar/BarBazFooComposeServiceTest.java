package com.yumi.learn.service.api.registrar;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
/**
 * (8 BeanRegistrar)
 * */
@SpringBootTest
public class BarBazFooComposeServiceTest {

	@Autowired
	private BarBazFooComposeService barBazFooComposeService;

    @Test
    void testBarBazFooComposeService() {
        barBazFooComposeService.composeCall();
    }
}