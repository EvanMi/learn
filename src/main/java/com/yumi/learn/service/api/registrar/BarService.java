package com.yumi.learn.service.api.registrar;
/**
 * (8 BeanRegistrar)
 * */
public class BarService {
    private final FooService fooService;

    public BarService(FooService fooService) {
        this.fooService = fooService;
    }

    public String bar() {
        return "bar";
    }

    public FooService getFooService() {
        return fooService;
    }
}
