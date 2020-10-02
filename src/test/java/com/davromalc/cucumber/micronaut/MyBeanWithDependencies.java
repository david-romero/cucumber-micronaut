package com.davromalc.cucumber.micronaut;

public class MyBeanWithDependencies {

    private final MyBean myBean;

    public MyBeanWithDependencies(MyBean myBean) {
        this.myBean = myBean;
    }

    public void doStuff() {
        myBean.doStuff();
    }

}
