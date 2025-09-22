package com.yumi.learn.service.api.fallback;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * (9 Fallback)
 * */
@SpringBootTest
public class NiceDayServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(NiceDayServiceTest.class);

    @Autowired
    private NiceDayService niceDayService;

    @Test
    void testNiceDay() {
        LOGGER.info("nice day result: {}", niceDayService.niceDay());
    }
}
