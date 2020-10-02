package com.davromalc.cucumber.micronaut.beans;

import javax.inject.Named;

public class MyBeanClient {

    public final MyBeanInterface bean;

    public MyBeanClient(@Named("myDecoratorBeanImplementation") MyBeanInterface bean) {
        this.bean = bean;
    }

    public void main() {
        bean.doStuff();
    }
}
