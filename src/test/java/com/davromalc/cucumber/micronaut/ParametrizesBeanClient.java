package com.davromalc.cucumber.micronaut;

import javax.inject.Named;

public class ParametrizesBeanClient {

    final ParametrizedBean<Long, String> myBean;

    public ParametrizesBeanClient(@Named("longToStringBasedParametrizesBean") ParametrizedBean<Long, String> myBean) {
        this.myBean = myBean;
    }

    public void main() {
        System.out.println(myBean.doStuff(1L));
    }
}
