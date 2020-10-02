package com.davromalc.cucumber.micronaut;

import javax.inject.Named;

public class MyBeanClient {

    final MyBeanInterface bean;

    public MyBeanClient(@Named("myDecoratorBeanImplementation") MyBeanInterface bean) {
        this.bean = bean;
    }

    public void main() {
        bean.doStuff();
    }
}
