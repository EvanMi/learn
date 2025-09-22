package com.yumi.learn.service.api.registrar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
/**
 * (8 BeanRegistrar)
 * */
@Service
public class BarBazFooComposeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BarBazFooComposeService.class);
    private final BarService barService;
    private final FooService fooService;
    private final BazService bazService;

    public BarBazFooComposeService(BarService barService, FooService fooService, BazService bazService) {
        this.barService = barService;
        this.fooService = fooService;
        this.bazService = bazService;
    }

    public void composeCall() {
        LOGGER.info("barService.bar() = {}", barService.bar());
        LOGGER.info("barService.getFooService() = {}", barService.getFooService().foo());
        LOGGER.info("fooService.foo() = {}", fooService.foo());
        LOGGER.info("bazService.baz() = {}", bazService.baz());
    }
}
