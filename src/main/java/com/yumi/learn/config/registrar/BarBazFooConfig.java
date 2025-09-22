package com.yumi.learn.config.registrar;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
/**
 * (8 BeanRegistrar)
 * */
@Configuration
@Import(BarBazFooBeanRegistrar.class)
public class BarBazFooConfig {
}
