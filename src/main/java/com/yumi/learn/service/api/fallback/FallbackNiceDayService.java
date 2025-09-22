package com.yumi.learn.service.api.fallback;

import org.springframework.context.annotation.Fallback;
import org.springframework.stereotype.Component;
/**
 * (9 Fallback)
 * */
@Component
@Fallback
public class FallbackNiceDayService implements NiceDayService{

    @Override
    public String niceDay() {
        return "default fallback nice day";
    }
}
