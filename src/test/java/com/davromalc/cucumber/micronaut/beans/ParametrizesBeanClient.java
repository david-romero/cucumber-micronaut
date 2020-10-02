package com.davromalc.cucumber.micronaut.beans;

public class ParametrizesBeanClient {

    public final ParametrizedBean<Long, String> myBean;

    public ParametrizesBeanClient(ParametrizedBean<Long, String> myBean) {
        this.myBean = myBean;
    }

    public void main() {
        System.out.println(myBean.doStuff(1L));
    }
}
