package com.yumi.learn.service.api.fallback;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.stereotype.Component;
/**
 * (9 Fallback)
 * */
@Component
@ConditionalOnBooleanProperty("learn.nice.day")
public class FirstNiceDayService implements NiceDayService{

    @Override
    public String niceDay() {
        return "first nice day";
    }
}
