package com.yumi.learn.service.api.registrar;
/**
 * (8 BeanRegistrar)
 * */
public class BazService {
    private final String information;

    public BazService(String information) {
        this.information = information;
    }

    public String baz() {
        return information;
    }

}
