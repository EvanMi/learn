package com.yumi.learn.config.registrar;

import com.yumi.learn.service.api.registrar.BarService;
import com.yumi.learn.service.api.registrar.BazService;
import com.yumi.learn.service.api.registrar.FooService;
import org.springframework.beans.factory.BeanRegistrar;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.core.env.Environment;
/**
 * (8 BeanRegistrar)
 * */
public class BarBazFooBeanRegistrar implements BeanRegistrar {
    @Override
    public void register(BeanRegistry registry, Environment env) {

        registry.registerBean("fooService", FooService.class);

        registry.registerBean("barService", BarService.class, spec -> {
            spec.prototype()
                    .lazyInit()
                    .description("this is bar service")
                    .supplier(context -> new BarService(context.bean(FooService.class)));
        });

        registry.registerBean(BazService.class, spec -> spec
                .fallback()
                .supplier(context -> new BazService("normalBaz")));

        if (env.getProperty("learn.baz.enable", Boolean.class, false)) {
            registry.registerBean(BazService.class, spec -> spec
                    .supplier(context -> new BazService("happyBaz")));
        }
    }
}
